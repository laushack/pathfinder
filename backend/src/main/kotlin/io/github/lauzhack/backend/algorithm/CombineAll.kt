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

    val paths = mutableListOf<Trip>()
    for (ppr in pprs) {
      val path = algorithm.run(ppr.first.stationId, startTime + ppr.second, targetStation!!.id)
      if (path != null) {
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

  fun minutesToTime(minutes: Time): String {
    val hours = minutes / 60
    val minutes = minutes % 60
    return "%02d:%02d:00".format(hours, minutes)
  }
}
