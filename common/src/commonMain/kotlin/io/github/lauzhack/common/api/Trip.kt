package io.github.lauzhack.common.api

import kotlinx.serialization.Serializable

@Serializable
data class Trip(
    val stops: List<TripStop>,
)

fun Trip.compact(): CompactTrip {
  val subTrips = mutableMapOf<String, MutableList<Pair<Int, TripStop>>>()
  for ((index, stop) in stops.withIndex()) {
    for (tripId in stop.tripIds) {
      subTrips.getOrPut(tripId) { mutableListOf() }.add(index to stop)
    }
  }
  return CompactTrip(
      subTrips.values
          .sortedBy { it.first().first }
          .map { Trip(it.map { it.second }) }
          .filter { it.stops.size > 1 })
}

data class CompactTrip(val subTrips: List<Trip>)

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
