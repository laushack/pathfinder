package io.github.lauzhack.backend.features.session

import io.github.lauzhack.backend.api.openAI.OpenAIMessage
import io.github.lauzhack.backend.api.openAI.OpenAIRequest
import io.github.lauzhack.backend.api.openAI.OpenAIService
import io.github.lauzhack.backend.data.Resources
import io.github.lauzhack.common.QueryParameters
import io.github.lauzhack.common.api.*
import io.github.lauzhack.common.api.AssistantRole.Assistant
import io.github.lauzhack.common.api.AssistantRole.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Session(
    private val enqueue: (BackendToUserMessage) -> Unit,
    private val openAIService: OpenAIService,
) {

  private val conversation = mutableListOf<OpenAIMessage>()

  private fun printConversation() {
    println("Conversation: ===============")
    conversation.forEach {
      println("=======================")
      println("Role: ${it.role}")
      println("Content: ${it.content}")
      println("=======================")
    }
    println("Conversation END: =======================")
  }

  suspend fun process(message: UserToBackendMessage) {
    when (message) {
      is UserToAssistantMessage -> {

        val askForJsonOutputPrompt = Resources.Prompt.UserPrompt
        val askForResponsePrompt = Resources.Prompt.QueryPrompt

        conversation.add(OpenAIMessage(role = "user", content = message.text))
        val response =
            openAIService.prompt(
                OpenAIRequest(
                    messages =
                        conversation +
                            OpenAIMessage(role = "system", content = askForJsonOutputPrompt)),
            )
        val choices = response.choices
        if (choices.isNotEmpty()) {
          val first = choices.first().message
          val json = first.content

          val queryParameters: QueryParameters? =
              try {
                val current =
                    Json {
                          isLenient = true
                          ignoreUnknownKeys = true
                        }
                        .decodeFromString(QueryParameters.serializer(), json)
                println(current)
                current
              } catch (e: Exception) {
                println("Error: $e")
                null // Explicitly return null in case of an exception
              }

          val currentJson =
              queryParameters?.let {
                Json { this.prettyPrint = true }.encodeToString<QueryParameters>(it)
              } ?: "{}"

          val prompt = askForResponsePrompt.replace("\$INJECT_CURRENT_JSON\$", currentJson)
          val response2 =
              openAIService.prompt(
                  OpenAIRequest(
                      messages = conversation + OpenAIMessage(role = "system", content = prompt)))

          // Asking chatGPT to generate a response for missing info
          val question = response2.choices.first().message.content

          conversation.add(
              OpenAIMessage(
                  role = "assistant",
                  content = question,
              ),
          )

          enqueue(
              AssistantToUserConversation(
                  conversation
                      .filter { it.role == "assistant" || it.role == "user" }
                      .map {
                        AssistantToUserMessage(
                            role = if (it.role == "user") User else Assistant,
                            text = it.content,
                        )
                      },
              ))

          printConversation()
        }
      }
    }
  }
}
