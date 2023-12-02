package io.github.lauzhack.common.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssistantToUserMessage(
    @SerialName("text") val text: String,
    @SerialName("suggestions") val suggestions: List<String> = emptyList(),
) : BackendToUserMessage()

@Serializable
data class UserToAssistantMessage(
    @SerialName("text") val text: String,
) : UserToBackendMessage()
