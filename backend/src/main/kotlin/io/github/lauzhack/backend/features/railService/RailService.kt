package io.github.lauzhack.backend.features.railService

import io.github.lauzhack.backend.algorithm.*
import io.github.lauzhack.backend.data.Resources

typealias StationId = Int

class RailService {

  private val schedule = Schedule.fromData()
  private val algorithm = Algorithm(schedule)
  private val closestPPR = ClosestPPRAlgorithm.fromData()

  fun computePath(startLocation: Location, endLocation: Location, startTime: Time): List<Node>? {
    val closestPPR = closestPPR.computeShortestPath(startLocation, endLocation, startTime)
    if (closestPPR.isEmpty()) {
      return null
    }
    return closestPPR.minBy { it.last().arrival }
  }

  fun pathToString(path: List<Node>): String {
    val result = StringBuilder()
    path.forEachIndexed { i, n ->
      result.append("$i: $n -> ${nameMap[n.id]} -- ${minutesToTime(n.arrival)}\n")
    }

    return result.toString()
  }

  private val nameMap: Map<NodeID, String> =
      Resources.Stops.data().associate {
        it[Resources.Stops.StopId].split(":")[0].toInt() to it[Resources.Stops.StopName]
      }

  private fun minutesToTime(minutes: Time): String {
    val hours = minutes / 60
    val minutes = minutes % 60
    return "%02d:%02d:00".format(hours, minutes)
  }
}
