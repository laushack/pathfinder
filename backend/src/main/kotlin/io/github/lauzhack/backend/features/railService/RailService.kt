package io.github.lauzhack.backend.features.railService

import io.github.lauzhack.backend.algorithm.*
import io.github.lauzhack.backend.data.Resources

typealias StationId = Int

class RailService {

  private companion object {
    private const val vlv = 8501116
    private const val loz = 8501120
    private const val stGallen = 8506302
    private const val frib = 8504100
    private const val romont = 8504023
  }

  private val schedule = Schedule.fromData()
  private val algorithm = Algorithm(schedule)

  fun computePath(startID: NodeID, startTime: Time, endId: StationId): List<Node>? {
    return algorithm.run(startID, startTime, endId)
  }

  fun pathToString(path: List<Node>?): String {
    val result = StringBuilder()
    path?.forEachIndexed { i, n ->
      result.append("$i: $n -> ${nameMap[n.id]} -- ${minutesToTime(n.arrival)}\n")
    } ?: result.append("No path lol")

    return result.toString()
  }

  fun getStartStationId(): StationId {
    return vlv
  }

  fun getEndStationId(): StationId {
    return stGallen
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
