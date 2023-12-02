package io.github.lauzhack.backend.api.openAI

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenAIRequest(
    @SerialName("model") val model: String = "gpt-3.5-turbo",
    @SerialName("messages") val messages: List<OpenAIMessage>,
)