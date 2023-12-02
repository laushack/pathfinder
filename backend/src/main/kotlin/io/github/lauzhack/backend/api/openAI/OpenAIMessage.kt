package io.github.lauzhack.backend.api.openAI

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenAIMessage(
    @SerialName("role") val role: String,
    @SerialName("content") val content: String,
)