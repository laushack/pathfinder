package io.github.lauzhack.backend.api.openStreetMap

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenStreetMapRequest(
  @SerialName("format") val format: String = "json",
  @SerialName("q") val q: String,
)