package io.github.lauzhack.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DadJoke(
    @SerialName("id") val id: String,
    @SerialName("joke") val joke: String,
    @SerialName("status") val status: Int,
)
