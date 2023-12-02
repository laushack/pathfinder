package io.github.lauzhack.backend.api.openAI

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenAIChoice(
    @SerialName("index") val index: Int,
    @SerialName("message") val message: OpenAIMessage,
    @SerialName("finish_reason") val finishReason: String,
)