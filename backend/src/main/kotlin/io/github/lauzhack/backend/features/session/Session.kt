package io.github.lauzhack.backend.features.session

import io.github.lauzhack.backend.algorithm.timeToMinutes
import io.github.lauzhack.backend.api.openAI.*
import io.github.lauzhack.backend.data.Resources.Prompt.ExtractJsonFromUserMessagePrompt
import io.github.lauzhack.backend.data.Resources.Prompt.GenerateQuestionForMissingJsonPrompt
import io.github.lauzhack.backend.features.railService.RailService
import io.github.lauzhack.common.api.*
import io.github.lauzhack.common.api.AssistantRole.Assistant
import io.github.lauzhack.common.api.AssistantRole.User
import io.github.lauzhack.common.api.PlanningOptions
import kotlinx.serialization.encodeToString

class EarlyAbortException : Exception()

class Session(
    private val enqueue: (BackendToUserMessage) -> Unit,
    private val openAIService: OpenAIService,
    private val railService: RailService,
    private val openStreetMapService: OpenStreetMapService
) {

  private var currentPlanning = PlanningOptions()
  private val conversation = mutableListOf<OpenAIMessage>()

  companion object {
    private const val ROLE_USER = "user"
    private const val ROLE_SYSTEM = "system"
    private const val ROLE_ASSISTANT = "assistant"
    private const val STRING_JSON_INJECT = "\$INJECT_CURRENT_JSON\$"
  }

  suspend fun process(message: UserToBackendMessage) {
    when (message) {
      is UserToAssistantMessage ->
          try {
            handleUserToAssistantMessage(message)
          } catch (e: EarlyAbortException) {
            println("Aborting early")
          }
      is UserToAssistantSetPlanning -> currentPlanning = message.planningOptions
    }
  }

  private suspend fun handleUserToAssistantMessage(message: UserToAssistantMessage) {
    updateConversation(message.text, ROLE_USER)
    val extractJsonPrompt = ExtractJsonFromUserMessagePrompt
    val extractPromptResponse = openAIResponseForConversation(extractJsonPrompt)
    val json = getFirstChoice(extractPromptResponse) ?: "{}"

    updatePlanning(json)

    val currentJson = DefaultJsonSerializer.encodeToString(currentPlanning)
    val questionPrompt =
        GenerateQuestionForMissingJsonPrompt.replace(STRING_JSON_INJECT, currentJson)
    val questionPromptResponse = openAIResponseForConversation(questionPrompt)
    val question = getFirstChoice(questionPromptResponse) ?: "An error occurred. Please try again."

    updateConversation(question, ROLE_ASSISTANT)
    enqueueConversationToUser()
    enqueuePlanningToUser()
  }

  private suspend fun updatePlanning(json: String) {
    val extracted = DefaultJsonSerializer.decodeFromString(PlanningOptions.serializer(), json)
    currentPlanning = currentPlanning.updatedWith(extracted)

    if (currentPlanning.isSufficient()) {
      println("Sufficient planning")
      computeAndSendTrip()
    }
  }

  private suspend fun computeAndSendTrip() {
    val startLocation = openStreetMapService.getLatLong(currentPlanning.startLocation!!)
    val endLocation = openStreetMapService.getLatLong(currentPlanning.endLocation!!)

    if (startLocation == null || endLocation == null) {
      println(
          "Could not find location for ${currentPlanning.startLocation} or ${currentPlanning.endLocation}")
      updateConversation(
          "Could not find start location ${currentPlanning.startLocation}, or end location ${currentPlanning.endLocation}. Please try other locations",
          ROLE_ASSISTANT)
      currentPlanning.invalidateStartLocation()
      currentPlanning.invalidateEndLocation()
      enqueueConversationToUser()
      throw EarlyAbortException()
    }

    println("Computing trip from $startLocation to $endLocation")

    val trip =
        railService
            .computePath(
                startLocation = startLocation,
                endLocation = endLocation,
                startTime = timeToMinutes(currentPlanning.startTime!!),
            )
            ?.let { railService.pathToString(it) } ?: "No path found"

    enqueueTripToUser(trip)
  }

  private fun getFirstChoice(response: OpenAIResponse) =
      response.choices.firstOrNull()?.message?.content

  private suspend fun openAIResponseForConversation(prompt: String, role: String = ROLE_SYSTEM) =
      openAIService.prompt(
          OpenAIRequest(messages = conversation + OpenAIMessage(role = role, content = prompt)))

  private fun updateConversation(message: String, role: String) {
    conversation.add(
        OpenAIMessage(
            role = role,
            content = message,
        ),
    )
  }

  private fun enqueueConversationToUser() {
    enqueue(
        AssistantToUserConversation(
            conversation
                .filter { it.role == ROLE_ASSISTANT || it.role == ROLE_USER }
                .map {
                  AssistantToUserMessage(
                      role = if (it.role == ROLE_USER) User else Assistant,
                      text = it.content,
                  )
                },
        ))
  }

  private fun enqueuePlanningToUser() {
    enqueue(
        AssistantToUserSetPlanning(
            planningOptions = currentPlanning,
        ))
  }

  private fun enqueueTripToUser(path: String) {
    enqueue(BackendToUserSetTrip(path))
  }
}
