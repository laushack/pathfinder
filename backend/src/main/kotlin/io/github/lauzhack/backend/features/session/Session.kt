package io.github.lauzhack.backend.features.session

import io.github.lauzhack.backend.api.openAI.OpenAIMessage
import io.github.lauzhack.backend.api.openAI.OpenAIRequest
import io.github.lauzhack.backend.api.openAI.OpenAIService
import io.github.lauzhack.common.api.*
import io.github.lauzhack.common.api.AssistantRole.Assistant
import io.github.lauzhack.common.api.AssistantRole.User

class Session(
    private val enqueue: (BackendToUserMessage) -> Unit,
    private val openAIService: OpenAIService,
) {
  private val conversation =
      mutableListOf(
          OpenAIMessage(
              role = "system",
              content =
                  "You are a travel assistant. Your role is to extract information from the user's dialogue, and output structured data a json that will then be parsed in a backend. Here are the information you need to extract: 'Starting location', 'Destination location', 'departure time'. Encore them into the json as 'start-location', 'end-location', 'start-time'. For the departure time, format it the following way: (hours:minutes) in 24h format. Only output the json content and nothing else."))

  suspend fun process(message: UserToBackendMessage) {
    when (message) {
      is UserToAssistantMessage -> {
        conversation.add(
            OpenAIMessage(role = "user", content = message.text),
        )
        val response = openAIService.prompt(OpenAIRequest(messages = conversation))
        val choices = response.choices
        if (choices.isNotEmpty()) {
          val first = choices.first().message
          conversation.add(first)
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
        }
      }
    }
  }
}
