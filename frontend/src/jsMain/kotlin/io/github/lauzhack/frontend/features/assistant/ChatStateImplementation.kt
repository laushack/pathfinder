package io.github.lauzhack.frontend.features.assistant

import androidx.compose.runtime.*
import io.github.lauzhack.common.api.*
import io.github.lauzhack.frontend.features.assistant.ChatState.Suggestion
import io.github.lauzhack.frontend.utils.ktor.deserializeFromFrame
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import kotlin.js.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

/** Returns an implementation of a [ChatState]. */
@Composable
fun rememberChatState(): ChatState {
  val scope = rememberCoroutineScope()
  return remember(scope) { ChatStateImplementation(scope) }
}

private class ChatStateImplementation(scope: CoroutineScope) : ChatState {

  private val httpClient = HttpClient {
    install(ContentNegotiation) { json() }
    install(WebSockets) {
      contentConverter = KotlinxWebsocketSerializationConverter(DefaultJsonSerializer)
    }

    defaultRequest {
      host = "localhost"
      port = 8888
    }
  }

  private val messages = mutableStateListOf<ChatState.Message>()

  private val queue = Channel<String>(Channel.UNLIMITED)

  override val conversation: List<ChatState.Message>
    get() = messages // FakeConversation

  override var input: String by mutableStateOf("")

  override fun onSendClick() {
    queue.trySend(input)
    messages.add(ChatState.Message(ChatState.Role.User, input))
    input = ""
  }

  override fun onSuggestionClick(suggestion: Suggestion) {
    queue.trySend(suggestion.text)
    messages.add(ChatState.Message(ChatState.Role.User, suggestion.text))
  }

  init {
    scope.launch {
      httpClient.webSocket("/live") {
        while (true) {
          select {
            queue.onReceive { message ->
              sendSerialized<UserToBackendMessage>(UserToAssistantMessage(message))
            }
            incoming.onReceive { message ->
              val response = deserializeFromFrame<BackendToUserMessage>(message)
              when (response) {
                is AssistantToUserConversation -> {
                  messages.clear()
                  messages.addAll(
                      response.messages.map {
                        ChatState.Message(
                            role =
                                when (it.role) {
                                  AssistantRole.Assistant -> ChatState.Role.Assistant
                                  AssistantRole.User -> ChatState.Role.User
                                },
                            content = it.text,
                            suggestions = it.suggestions.map(::Suggestion),
                        )
                      },
                  )
                }
                is AssistantToUserSetPlanning -> {}
              }
            }
          }
        }
      }
    }
  }
}

private val FakeConversation =
    listOf(
        ChatState.Message(
            role = ChatState.Role.User,
            content = "I want to go to Lausanne from Geneva",
            suggestions = listOf(),
        ),
        ChatState.Message(
            role = ChatState.Role.Assistant,
            content = "Thanks for this information. At what time do you want to leave ?",
            suggestions =
                listOf(
                    Suggestion("Now"),
                    Suggestion("In 30 minutes"),
                    Suggestion("In 1 hour"),
                ),
        ),
        ChatState.Message(
            role = ChatState.Role.User,
            content = "In 30 minutes",
            suggestions = listOf(),
        ),
        ChatState.Message(
            role = ChatState.Role.Assistant,
            content =
                "Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet.",
            suggestions =
                listOf(
                    Suggestion("Now"),
                    Suggestion("In 30 minutes"),
                    Suggestion("In 1 hour"),
                ),
        ),
        ChatState.Message(
            role = ChatState.Role.User,
            content = "Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. ".repeat(10),
            suggestions = listOf(),
        ),
        ChatState.Message(
            role = ChatState.Role.Assistant,
            content = "Ok. Do you have a ticket or half-fare card ?",
            suggestions =
                listOf(
                    Suggestion("Yes"),
                    Suggestion("No"),
                ),
        ),
        ChatState.Message(
            role = ChatState.Role.User,
            content = "Yes",
            suggestions = listOf(),
        ),
        ChatState.Message(
            role = ChatState.Role.Assistant,
            content = "Ok. Here is a list of trains you can take.",
        ),
    )
