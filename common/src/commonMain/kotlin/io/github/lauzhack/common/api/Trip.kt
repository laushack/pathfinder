package io.github.lauzhack.common.api

import kotlinx.serialization.Serializable

@Serializable
data class BackendToUserSetTrip(
  val trip: String,
) : BackendToUserMessage()