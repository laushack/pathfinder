package io.github.lauzhack.backend.features.session

import io.github.lauzhack.backend.api.openAI.OpenAIMessage
import io.github.lauzhack.backend.api.openAI.OpenAIRequest
import io.github.lauzhack.backend.api.openAI.OpenAIService
import io.github.lauzhack.backend.data.Resources
import io.github.lauzhack.common.QueryParameters
import io.github.lauzhack.common.api.*
import io.github.lauzhack.common.api.AssistantRole.Assistant
import io.github.lauzhack.common.api.AssistantRole.User
import kotlinx.serialization.json.Json

class Session(
    private val enqueue: (BackendToUserMessage) -> Unit,
    private val openAIService: OpenAIService,
) {

  private val userConversation =
      mutableListOf(OpenAIMessage(role = "system", content = Resources.Prompt.UserPrompt))

  private val queryConversation =
      mutableListOf(OpenAIMessage(role = "system", content = Resources.Prompt.QueryPrompt))

  private fun printUserConversation() {
    println("User conversation: ===============")
    userConversation.forEach {
      println("=======================")
      println("Role: ${it.role}")
      println("Content: ${it.content}")
      println("=======================")
    }
    println("User conversation END: =======================")
  }

  private fun printQueryConversation() {
    println("Query conversation: ===============")
    queryConversation.forEach {
      println("=======================")
      println("Role: ${it.role}")
      println("Content: ${it.content}")
      println("=======================")
    }
    println("Query conversation END: =======================")
  }

  suspend fun process(message: UserToBackendMessage) {
    when (message) {
      is UserToAssistantMessage -> {
        userConversation.add(
            OpenAIMessage(role = "user", content = message.text),
        )
        val response = openAIService.prompt(OpenAIRequest(messages = userConversation))
        val choices = response.choices
        if (choices.isNotEmpty()) {
          val first = choices.first().message
          val json = first.content

          val queryParameters: QueryParameters? =
              try {
                Json.decodeFromString(QueryParameters.serializer(), json)
              } catch (e: Exception) {
                println("Error: $e")
                null // Explicitly return null in case of an exception
              }

          // Asking chatGPT to generate a response for missing info
          queryConversation.add(
              OpenAIMessage(
                  role = "user",
                  content = json,
              ),
          )

          val question =
              openAIService
                  .prompt(OpenAIRequest(messages = queryConversation))
                  .choices
                  .first()
                  .message
                  .content

          userConversation.add(
              OpenAIMessage(
                  role = "assistant",
                  content = question,
              ),
          )

          enqueue(
              AssistantToUserConversation(
                  userConversation
                      .filter { it.role == "assistant" || it.role == "user" }
                      .map {
                        AssistantToUserMessage(
                            role = if (it.role == "user") User else Assistant,
                            text = it.content,
                        )
                      },
              ))

          printUserConversation()
          printQueryConversation()
        }
      }
    }
  }
}
