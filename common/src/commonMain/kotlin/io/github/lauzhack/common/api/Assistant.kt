package io.github.lauzhack.common.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AssistantRole {
  User,
  Assistant,
}

@Serializable
data class AssistantToUserConversation(
    @SerialName("messages") val messages: List<AssistantToUserMessage>,
) : BackendToUserMessage()

@Serializable
data class AssistantToUserMessage(
    @SerialName("role") val role: AssistantRole,
    @SerialName("text") val text: String,
    @SerialName("suggestions") val suggestions: List<String> = emptyList(),
)

@Serializable
data class UserToAssistantMessage(
    @SerialName("text") val text: String,
) : UserToBackendMessage()
