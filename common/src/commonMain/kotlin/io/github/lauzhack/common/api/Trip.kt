package io.github.lauzhack.common.api

import kotlinx.serialization.Serializable

@Serializable
data class Trip(
    val stops: List<TripStop>,
)

@Serializable
data class TripStop(
    val tripIds: Set<String>,
    val name: String,
    val arrivalTime: String?,
    val departureTime: String?,
    val latitude: Double,
    val longitude: Double,
)

@Serializable
data class BackendToUserSetTrip(
    val trip: Trip,
) : BackendToUserMessage()
