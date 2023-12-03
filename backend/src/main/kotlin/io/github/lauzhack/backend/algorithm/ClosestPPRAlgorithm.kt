package io.github.lauzhack.backend.algorithm

import io.github.lauzhack.backend.data.Resources
import io.github.lauzhack.backend.data.Resources.Mobilitat.Geopos
import io.github.lauzhack.backend.data.Resources.Mobilitat.OPUIC
import io.github.lauzhack.backend.data.Resources.Mobilitat.Parkrail
import io.github.lauzhack.backend.data.Resources.Mobilitat.ParkrailPriceDay
import io.github.lauzhack.backend.data.Resources.Mobilitat.ParkrailPriceMonth
import io.github.lauzhack.backend.data.Resources.Mobilitat.ParkrailPriceYear
import io.github.lauzhack.common.api.PPRData
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

enum class OptimizePrice {
  NONE,
  DAY,
  MONTH,
  YEAR
}

/** Algorithm for the first segment, location to PPR using car */
class ClosestPPRAlgorithm(val pprData: List<PPR>) {
  /**
   * Returns the reasonably closest PPRs to the given location, and the estimated time to go there.
   */
  fun findClosestPPR(
      location: Location,
      time: Time,
      optimization: OptimizePrice = OptimizePrice.NONE,
  ): List<Pair<PPR, Time>> {
    val totalDistance = pprData.sumOf { distance(location, it.location) }
    val totalCost =
        pprData.sumOf {
          when (optimization) {
            OptimizePrice.NONE -> 0.0
            OptimizePrice.DAY -> it.priceDay
            OptimizePrice.MONTH -> it.priceMonth
            OptimizePrice.YEAR -> it.priceYear
          }
        }
    return pprData
        .filter { ppr -> ppr.bindingTime?.contains(time) ?: true }
        .sortedBy { ppr -> score(ppr, location, optimization, totalDistance, totalCost) }
        .take(5)
        .map { ppr -> Pair(ppr, (distance(ppr.location, location) / (60 * 13.8)).toLong()) }
  }

  companion object {
    /** Build an algorithm from the dataset */
    fun fromData(): ClosestPPRAlgorithm {
      val pprData =
          Resources.Mobilitat.data().map {
            val location = parseGeoPos(it[Geopos])
            val id = it[OPUIC].toInt()
            val priceDay =
                try {
                  it[ParkrailPriceDay].toDouble()
                } catch (e: Exception) {
                  0.0
                }
            val priceMonth =
                try {
                  it[ParkrailPriceMonth].toDouble()
                } catch (e: Exception) {
                  0.0
                }
            val priceYear =
                try {
                  it[ParkrailPriceYear].toDouble()
                } catch (e: Exception) {
                  0.0
                }
            val capacity =
                try {
                  it[Parkrail].toInt()
                } catch (e: Exception) {
                  0
                }
            val bindingTime = stringToBindingTime(it[Resources.Mobilitat.ParkrailBindingTime1])
            PPR(id, location, priceDay, priceMonth, priceYear, capacity, bindingTime)
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
data class PPR(
    val stationId: Int,
    val location: Location,
    val priceDay: Double,
    val priceMonth: Double,
    val priceYear: Double,
    val capacity: Int,
    val bindingTime: BindingTime?,
)

fun toPPRData(ppr: PPR, stationLocation: Location): PPRData {
  val timeByFeet = distance(ppr.location, stationLocation) / (60)
  return PPRData(
      ppr.priceDay,
      ppr.priceMonth,
      ppr.priceYear,
      ppr.capacity,
      ppr.location.lat,
      ppr.location.lon,
      timeByFeet.toInt(),
      ppr.bindingTime?.from?.let { minutesToTime(it) } ?: "",
      ppr.bindingTime?.to?.let { minutesToTime(it) } ?: "")
}


fun minutesToTime(minutes: Time): String {
    val hours = minutes / 60
    val minutes = minutes % 60
    return "%02d:%02d:00".format(hours, minutes)
}

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

fun score(
    a: PPR,
    from: Location,
    optimization: OptimizePrice,
    totalDistance: Double,
    totalCost: Double
): Double {
  val distance = distance(a.location, from) / totalDistance
  val price =
      when (optimization) {
        OptimizePrice.NONE -> 0.0
        OptimizePrice.DAY -> a.priceDay
        OptimizePrice.MONTH -> a.priceMonth
        OptimizePrice.YEAR -> a.priceYear
      } / totalCost
  val W_D = 0.6
  val W_P = 0.4
  return W_D * distance + W_P * price
}

data class BindingTime(val from: Time, val to: Time) {
  fun contains(time: Time): Boolean {
    return time in from..to
  }
}
