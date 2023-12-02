package io.github.lauzhack.frontend.features.assistant

/** The state of the chat input with the assistant. */
interface ChatState {

  /** The possible roles in the conversation. */
  enum class Role {
    User,
    Assistant,
  }

  /**
   * A message in the conversation.
   *
   * @property role The role of the user who sent the message.
   * @property content The content of the message.
   * @property suggestions The suggestions to display to the user.
   */
  data class Message(
      val role: Role,
      val content: String,
      val suggestions: List<Suggestion> = emptyList(),
  )

  /** A suggestion to display to the user. */
  data class Suggestion(
      val text: String,
  )

  /** The conversation with the assistant. */
  val conversation: List<Message>

  /** The current user input. */
  var input: String

  /** A callback which will be called when the user presses the button to send a message. */
  fun onSendClick()

  /** A callback which will be called when the user presses a suggestion. */
  fun onSuggestionClick(suggestion: Suggestion)
}
