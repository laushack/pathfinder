package io.github.lauzhack.backend.features.session

import io.github.lauzhack.backend.api.openAI.OpenAIMessage
import io.github.lauzhack.backend.api.openAI.OpenAIRequest
import io.github.lauzhack.backend.api.openAI.OpenAIService
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
      mutableListOf(
          OpenAIMessage(
              role = "system",
              content =
                  "You are a travel assistant. Please read the user's message carefully. The user will provide details about a train journey, including a starting location, a destination location, and a start time. Your task is to extract this information from the message and present it in a JSON format. If any information is missing from the user's message, do not include that parameter in the JSON output. Remember to format the times in a 24-hour format (e.g., 16h32). Here is the structure you should use for the JSON:\n" +
                      "\n" +
                      "“{\n" +
                      "  \"start-location\": \"Starting location (e.g., Zurich)\",\n" +
                      "  \"end-location\": \"Destination location (e.g., Geneva)\",\n" +
                      "  \"start-time\": \"hour:minutes (e.g., 16h32, 24h format)\"\n" +
                      "}”\n" +
                      "\n" +
                      "Be precise in extracting the information, and ensure the JSON output is correctly formatted. If the user does not specify one or more of these details, omit that field from the JSON. For example, if the start location is missing, the JSON should only include the end location and start time, if provided. Pay close attention to the user's input for accurate information extraction. Only output the json content and nothing else."))

  private val queryConversation =
      mutableListOf(
          OpenAIMessage(
              role = "system",
              content =
                  "You are a travel assistant. Please read the user's message carefully. The user will provide details about a train journey, including a starting location, a destination location, and a start time. However, these will be in JSON format. Some of the information may be missing. Your task is to design a question to ask the user, to fill the missing information. You should only ask about one missing element, the most important one that is not yet specified, as the rest will be asked at a later point.\n" +
                      "\n" +
                      "The following are the mandatory information that need to be provided:\n" +
                      "- 'start-location' (The starting location of the journey)\n" +
                      "- 'end-location' (The final destination of the journey)\n" +
                      "- 'start-time' (The time at which the journey starts)\n" +
                      "\n" +
                      "The following are the optional information  that can be provided, but not mandatory:\n" +
                      "- 'start-date' (The day at which the journey must start)\n" +
                      "- 'end-time' (The time at which the journey must finish)\n" +
                      "- 'Subscription' (The name of the train subscription of the user)\n" +
                      "\n" +
                      "Do not use the JSON names when formulating the question, but use common language words. If all mandatory information but none of the optional ones  has been provided, you can inform the user of all optional information that he can provide.\n" +
                      "\n" +
                      "Be clear and concise in your request to ensure the user understands the importance of providing the missing mandatory information."))

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
                  role = "assistant",
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
        }
      }
    }
  }
}
