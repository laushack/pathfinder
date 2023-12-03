package io.github.lauzhack.backend.algorithm

import io.github.lauzhack.backend.data.Resources
import io.github.lauzhack.backend.data.Resources.Stops.StopId
import io.github.lauzhack.backend.data.Resources.Stops.StopLat
import io.github.lauzhack.backend.data.Resources.Stops.StopLon

data class Station(val id: NodeID, val location: Location)

class ClosestStationAlgorithm(val stations: List<Station>) {
  /** Returns the closest station to the given location.*/
  fun findClosestStation(location: Location): Station? {
    return stations.minByOrNull { distance(location, it.location) }
  }

  companion object {
    fun fromData(): ClosestStationAlgorithm {
      val stationData =
          Resources.Stops.data().mapNotNull {
            try {
            val location = Location(it[StopLat].trim().toDouble(), it[StopLon].trim().toDouble())
            Station(it[StopId].split(':')[0].toInt(), location)
            } catch (e: Exception) {
              println("Error parsing station ${it[StopId]}  lat ${it[StopLat]} lon ${it[StopLon]} error ${e.message}")
              null
            }
          }
      return ClosestStationAlgorithm(stationData)
    }
  }
}
