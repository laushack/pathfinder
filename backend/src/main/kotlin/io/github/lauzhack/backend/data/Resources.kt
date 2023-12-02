package io.github.lauzhack.backend.data

import com.opencsv.CSVReader

object Resources {

  /** Loads a CSV file with the given resource name. */
  private fun load(file: String) =
      this::class
          .java
          .classLoader
          .getResourceAsStream(file)!!
          .reader()
          .let(::CSVReader)
          .apply { skip(1) } // Skips the header row.
          .readAll()

  /** Contains the data from the `mobilitat.csv` file. */
  object Mobilitat {

    // Indices of the values in the arrays.
    val DidokNumber = 0
    val Parkrail = 1
    val ParkrailPriceDay = 2
    val ParkrailPriceMonth = 3
    val ParkrailPriceYear = 4
    val ParkrailBindingTime1 = 5
    val ParkrailBindingTime2 = 6
    val ParkrailBindingTime3 = 7
    val ParkrailComment = 8
    val Railtaxitext = 9
    val Railtaxiinfo = 10
    val RentalBikeDesignation = 11
    val RentalBikeNumber = 12
    val RentalBikeComment = 13
    val BikeParkingStatusD = 14
    val BikeParkingCommentD = 15
    val BikeParkingLockable = 16
    val BikeParkingLockableText = 17
    val CarsharingLocationDesignation = 18
    val ParkrailApp = 19
    val ParkrailWebshop = 20
    val ParkrailLocal = 21
    val ParkrailAutomat = 22
    val OPUIC = 23
    val StopName = 24
    val StationAbbreviation = 25
    val Lod = 26
    val Geopos = 27
    val TransportCompany = 28
    val Weather = 29
    val PostalCode = 30

    /** Reads all the data from a file named `mobilitat.csv` in the resources folder. */
    fun data() = load("mobilitat.csv")
  }
}
