package io.github.lauzhack.backend.algorithm

import io.github.lauzhack.backend.data.Resources
import io.github.lauzhack.backend.data.Resources.Mobilitat.Geopos
import io.github.lauzhack.backend.data.Resources.Mobilitat.OPUIC

/** Algorithm for the first segment, location to PPR using car */
class ClosestPPRAlogrithm(val pprData: List<PPR>) {
  /**
   * Returns the reasonably closest PPRs to the given location, and the estimated time to go there.
   */
  fun findClosestPPR(location: Location): List<Pair<PPR, Time>> {
    return pprData
        .sortedBy { ppr -> distance(location, ppr.location) }
        .take(5)
        .map { ppr -> Pair(ppr, (distance(ppr.location, location) / (60 * 13.8)).toLong()) }
  }

  companion object {
    /** Build an algorithm from the dataset */
    fun fromData(): ClosestPPRAlogrithm {
      val pprData =
          Resources.Mobilitat.data().map {
            val location = parseGeoPos(it[Geopos])
            PPR(it[OPUIC].toInt(), location)
          }
      return ClosestPPRAlogrithm(pprData)
    }
  }
}

fun parseGeoPos(location: String): Location {
  val split = location.split(',')
  val lat = split[0].trim().toDouble()
  val lon = split[1].trim().toDouble()
  return Location(lat, lon)
}

/** Each park plus rail points, parsed from Mobilitat.csv. */
data class PPR(val stationId: Int, val location: Location)

data class Location(val lat: Double, val lon: Double)

/* Returns the distance between the two locations given their longitude and latitude, in meter. */
fun distance(a: Location, b: Location): Double {
  val R = 6371e3 // metres
  val φ1 = Math.toRadians(a.lat)
  val φ2 = Math.toRadians(b.lat)
  val Δφ = Math.toRadians(b.lat - a.lat)
  val Δλ = Math.toRadians(b.lon - a.lon)

  val x =
      Math.sin(Δφ / 2) * Math.sin(Δφ / 2) +
          Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ / 2) * Math.sin(Δλ / 2)
  val y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x))

  return R * y
}
