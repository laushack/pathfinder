package io.github.lauzhack.backend.algorithm

/** Combine all our algorithm to compute the shortest path with p + r */
class CombineAllAlgorithm(
    val closestPPR: ClosestPPRAlgorithm,
    val algorithm: Algorithm,
    val closestStation: ClosestStationAlgorithm
) {
  fun computeShortestPath(from: Location, to: Location, startTime: Time): List<List<Node>> {
    val pprs = closestPPR.findClosestPPR(from)
    val targetStation = closestStation.findClosestStation(to)

    val paths = mutableListOf<List<Node>>()
    for (ppr in pprs) {
      val path = algorithm.run(ppr.first.stationId, startTime + ppr.second, targetStation!!.id)
      if (path != null) {
        paths.add(path)
      }
    }
    return paths
  }

  companion object {
    fun fromData(): CombineAllAlgorithm {
      val closestPPR = ClosestPPRAlgorithm.fromData()
      val algorithm = Algorithm(Schedule.fromData())
      val closestStation = ClosestStationAlgorithm.fromData()
      return CombineAllAlgorithm(closestPPR, algorithm, closestStation)
    }
  }
}
