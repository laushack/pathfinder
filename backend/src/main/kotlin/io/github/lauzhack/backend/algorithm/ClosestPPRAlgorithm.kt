package io.github.lauzhack.backend.algorithm

import io.github.lauzhack.backend.data.Resources
import io.github.lauzhack.backend.data.Resources.Mobilitat.Geopos
import io.github.lauzhack.backend.data.Resources.Mobilitat.OPUIC
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/** Algorithm for the first segment, location to PPR using car */
class ClosestPPRAlgorithm(val pprData: List<PPR>) {
  /**
   * Returns the reasonably closest PPRs to the given location, and the estimated time to go there.
   */
  fun findClosestPPR(location: Location, time: Time): List<Pair<PPR, Time>> {
    return pprData
        .filter { ppr -> ppr.bindingTime?.contains(time) ?: true }
        .sortedBy { ppr -> distance(location, ppr.location) }
        .take(5)
        .map { ppr -> Pair(ppr, (distance(ppr.location, location) / (60 * 13.8)).toLong()) }
  }

  companion object {
    /** Build an algorithm from the dataset */
    fun fromData(): ClosestPPRAlgorithm {
      val pprData =
          Resources.Mobilitat.data().map {
            PPR(
                it[OPUIC].toInt(),
                parseGeoPos(it[Geopos]),
                stringToBindingTime(it[Resources.Mobilitat.ParkrailBindingTime1]))
          }
      return ClosestPPRAlgorithm(pprData)
    }

    private fun stringToBindingTime(time: String): BindingTime? {
      val split = time.split('-')
      if (split.isEmpty()) {
        return null
      }

      return try {
        val delimiter = '.'
        val from = timeToMinutes(split[0].trim(), delimiter)
        val to = timeToMinutes(split[1].trim(), delimiter)
        BindingTime(from, to)
      } catch (e: NumberFormatException) {
        null
      }
    }

    private fun parseGeoPos(location: String): Location {
      val split = location.split(',')
      val lat = split[0].trim().toDouble()
      val lon = split[1].trim().toDouble()
      return Location(lat, lon)
    }
  }
}

/** Each park plus rail points, parsed from Mobilitat.csv. */
data class PPR(val stationId: Int, val location: Location, val bindingTime: BindingTime?)

data class Location(val lat: Double, val lon: Double)

/* Returns the distance between the two locations given their longitude and latitude, in meter. */
fun distance(a: Location, b: Location): Double {
  val R = 6371e3 // metres
  val φ1 = Math.toRadians(a.lat)
  val φ2 = Math.toRadians(b.lat)
  val Δφ = Math.toRadians(b.lat - a.lat)
  val Δλ = Math.toRadians(b.lon - a.lon)

  val x = sin(Δφ / 2) * sin(Δφ / 2) + cos(φ1) * cos(φ2) * sin(Δλ / 2) * sin(Δλ / 2)
  val y = 2 * atan2(sqrt(x), sqrt(1 - x))

  return R * y
}

data class BindingTime(val from: Time, val to: Time) {
  fun contains(time: Time): Boolean {
    return time in from..to
  }
}
