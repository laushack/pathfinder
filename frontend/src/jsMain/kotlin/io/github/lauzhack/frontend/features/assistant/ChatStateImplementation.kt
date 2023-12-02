package io.github.lauzhack.frontend.features.assistant

import androidx.compose.runtime.*
import io.github.lauzhack.common.api.*
import io.github.lauzhack.frontend.api.backend.BackendService
import io.github.lauzhack.frontend.api.backend.LocalBackendService
import io.github.lauzhack.frontend.features.assistant.ChatState.Suggestion
import io.ktor.client.plugins.websocket.*

/** Returns an implementation of a [ChatState]. */
@Composable
fun rememberChatState(): ChatState {
  val backend = LocalBackendService.current
  return remember(backend) { ChatStateImplementation(backend) }
}

private class ChatStateImplementation(private val backend: BackendService) : ChatState {

  private fun AssistantToUserMessage.toMessage(): ChatState.Message {
    return ChatState.Message(
        role =
            when (this.role) {
              AssistantRole.Assistant -> ChatState.Role.Assistant
              AssistantRole.User -> ChatState.Role.User
            },
        content = this.text,
    )
  }

  override val conversation: List<ChatState.Message>
    get() = backend.conversation.map { it.toMessage() } // + FakeConversation + FakeConversation

  override var input: String by mutableStateOf("")

  override fun onSendClick() {
    backend.sendChatMessage(input)
    input = ""
  }

  override fun onSuggestionClick(suggestion: Suggestion) {
    backend.sendChatMessage(suggestion.text)
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
            content = "Ok. Here is a list of trains you can take:",
            suggestions =
                listOf(
                    Suggestion("Now"),
                    Suggestion("In 30 minutes"),
                    Suggestion("In 1 hour"),
                ),
        ),
    )
