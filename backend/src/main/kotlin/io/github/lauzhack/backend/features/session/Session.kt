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
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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
    private const val STRING_TIME_INJECT = "\$INJECT_CURRENT_TIME\$"
  }

  suspend fun process(message: UserToBackendMessage) {
    when (message) {
      is UserToAssistantMessage ->
          try {
            handleUserToAssistantMessage(message)
          } catch (e: EarlyAbortException) {
            println("Aborting early")
          }
      is UserToAssistantSetPlanning ->
          updatePlanning(DefaultJsonSerializer.encodeToString(message.planningOptions))
    }
  }

  private suspend fun handleUserToAssistantMessage(message: UserToAssistantMessage) {
    updateConversation(message.text, ROLE_USER)
    val currentTimeDate = currentTimeHumanFormat()
    val extractJsonPrompt =
        ExtractJsonFromUserMessagePrompt.replace(STRING_TIME_INJECT, currentTimeDate)
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

  private fun currentTimeHumanFormat(): String {
    val currentMoment = Clock.System.now()
    val currentDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

    // Formatting the date manually since kotlinx-datetime doesn't support DateTimeFormatter
    val dayOfWeek = currentDateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
    val dayOfMonth = currentDateTime.dayOfMonth
    val month = currentDateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }
    val year = currentDateTime.year
    val hour = currentDateTime.hour
    val minute = currentDateTime.minute.toString().padStart(2, '0')

    return "$dayOfWeek, ${dayOfMonth}th of $month $year ${hour}h$minute"
  }

  private suspend fun updatePlanning(json: String) {
    val extracted = DefaultJsonSerializer.decodeFromString(PlanningOptions.serializer(), json)
    currentPlanning = currentPlanning.updatedWith(extracted)

    if (currentPlanning.isSufficient()) {
      computeAndSendTrip()
    }
  }

  private suspend fun computeAndSendTrip() = coroutineScope {
    val startLocationDeferred = async {
      openStreetMapService.getLatLong(currentPlanning.startLocation!!)
    }
    val endLocationDeferred = async {
      openStreetMapService.getLatLong(currentPlanning.endLocation!!)
    }

    val startLocation = startLocationDeferred.await()
    val endLocation = endLocationDeferred.await()

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

    val splits = currentPlanning.startTime?.split(":") ?: emptyList()
    if (splits.size != 2 || splits[0].toIntOrNull() == null || splits[1].toIntOrNull() == null) {
      println("Invalid time format ${currentPlanning.startTime}")
      updateConversation(
          "Invalid time format ${currentPlanning.startTime}. Please try again", ROLE_ASSISTANT)
      enqueueConversationToUser()
      throw EarlyAbortException()
    }

    val trip =
        railService.computePath(
            startLocation = startLocation,
            endLocation = endLocation,
            startTime = timeToMinutes(currentPlanning.startTime!!),
        )

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

  private fun enqueueTripToUser(trip: Trip) {
    enqueue(BackendToUserSetTrip(trip))
  }
}
