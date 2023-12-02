package io.github.lauzhack.backend.api.openAI

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenAIResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val obj: String,
    @SerialName("created") val created: Long,
    @SerialName("model") val model: String,
    @SerialName("choices") val choices: List<OpenAIChoice>,
    @SerialName("usage") val usage: OpenAIUsage,
    @SerialName("system_fingerprint") val systemFingerprint: String?,
)