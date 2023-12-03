package io.github.lauzhack.backend.algorithm

import io.github.lauzhack.backend.data.Resources
import io.github.lauzhack.common.api.PPRData
import io.github.lauzhack.common.api.Trip
import io.github.lauzhack.common.api.TripStop

data class NameLocation(val name: String, val location: Location)
/** Combine all our algorithm to compute the shortest path with p + r */
class CombineAllAlgorithm(
    private val closestPPR: ClosestPPRAlgorithm,
    private val algorithm: Algorithm,
    private val closestStation: ClosestStationAlgorithm,
    private val infoMap: Map<NodeID, NameLocation>,
    private val pprData: List<PPRData>,
) {
  fun computeShortestPath(from: Location, to: Location, startTime: Time): List<Trip> {
    val pprs = closestPPR.findClosestPPR(from, startTime)
    val targetStation = closestStation.findClosestStation(to)

    var paths = mutableListOf<Trip>()

      println(pprs.size)
      println(pprs)

    for (ppr in pprs) {
      val path = algorithm.run(ppr.first.stationId, startTime + ppr.second, targetStation!!.id)
      if (path != null) {
          println("BIte")
          println(path)
        var previousTripID: String? = null
        val trip =
            path.map {
              val location = infoMap[it.id]!!.location
              val ts =
                  TripStop(
                      previousTripID?.let { p -> setOf(p, it.tripID) } ?: setOf(it.tripID),
                      infoMap[it.id]!!.name,
                      arrivalTime = minutesToTime(it.arrival),
                      departureTime = it.departure?.let { d -> minutesToTime(d) },
                      latitude = location.lat,
                      longitude = location.lon)
              previousTripID = it.tripID
              ts
            }

        val pprData = toPPRData(ppr.first, Location(trip[0].latitude, trip[0].longitude))
        paths.add(Trip(pprData, trip))
      }
    }

    val startTripStop =
        listOf(
            TripStop(
                tripIds = setOf("StartId"),
                name = "Start",
                arrivalTime = null,
                departureTime = null,
                latitude = from.lat,
                longitude = from.lon))

    val endTripStop =
        listOf(
            TripStop(
                tripIds = setOf("EndId"),
                name = "End",
                arrivalTime = null,
                departureTime = null,
                latitude = to.lat,
                longitude = to.lon))

    paths = paths.map { Trip(it.pprData, startTripStop + it.stops + endTripStop) }.toMutableList()

    return paths
  }

    fun allPPR(): List<PPRData> {
        return pprData
    }

  companion object {
    fun fromData(): CombineAllAlgorithm {
      val closestPPR = ClosestPPRAlgorithm.fromData()
      val algorithm = Algorithm(Schedule.fromData())
      val closestStation = ClosestStationAlgorithm.fromData()
      val infoMap =
          Resources.Stops.data().associate {
            it[Resources.Stops.StopId].split(":")[0].toInt() to
                NameLocation(
                    it[Resources.Stops.StopName],
                    Location(
                        it[Resources.Stops.StopLat].toDouble(),
                        it[Resources.Stops.StopLon].toDouble()))
          }

        val stationMap = closestStation.stations.associate { it.id to it.location }
        val pprData = closestPPR.pprData.mapNotNull { stationMap[it.stationId]?.let { location  -> toPPRData(it, location)} }
      return CombineAllAlgorithm(closestPPR, algorithm, closestStation, infoMap, pprData)
    }
  }
  private fun minutesToTime(minutes: Time): String {
    val h = minutes / 60
    val m = minutes % 60
    return "%02d:%02d".format(h, m)
  }
}
