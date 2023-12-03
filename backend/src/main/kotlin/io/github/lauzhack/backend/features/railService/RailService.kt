package io.github.lauzhack.backend.features.railService

import io.github.lauzhack.backend.algorithm.*
import io.github.lauzhack.common.api.Trip

class RailService {

  private val combinedAlgo = CombineAllAlgorithm.fromData()

  fun computePath(startLocation: Location, endLocation: Location, startTime: Time): Trip {
    val shortestPaths = combinedAlgo.computeShortestPath(startLocation, endLocation, startTime)
    if (shortestPaths.isEmpty()) {
      println("Empty closestPPR")
      return Trip(pprData = null, emptyList())
    }
    return shortestPaths.minBy {
      it.stops.last().arrivalTime?.let { t -> timeToMinutes(t) } ?: Time.MAX_VALUE
    }
  }
}
