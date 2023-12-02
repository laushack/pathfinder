package io.github.lauzhack.backend.data

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder

object Resources {

  /** Loads a CSV file with the given resource name. */
  private fun load(file: String, separator: Char = ',') =
      this::class
          .java
          .classLoader
          .getResourceAsStream(file)!!
          .reader()
          .let {
            CSVReaderBuilder(it)
                .withCSVParser(CSVParserBuilder().withSeparator(separator).build())
                .build()
          }
          .apply { skip(1) } // Skips the header row.
          .readAll()


  private fun loadText(file: String) =
      this::class
          .java
          .classLoader
          .getResourceAsStream(file)!!
          .reader()
          .readText()

  object Prompt {
    val GenerateQuestionForMissingJsonPrompt = loadText("GenerateQuestionForMissingJson.prompt")
    val ExtractJsonFromUserMessagePrompt = loadText("ExtractJsonFromUserMessage.prompt")
  }


  /** Contains the data from the `agency.txt` file. */
  object Agency {

    // Indices of the values in the arrays.
    val AgencyId = 0
    val AgencyName = 1
    val AgencyUrl = 2
    val AgencyTimezone = 3
    val AgencyLang = 4
    val AgencyPhone = 5

    /** Reads all the data from a file named `agency.txt` in the resource folder. */
    fun data() = load("agency.txt")
  }

  /** Contains the data from the `calendar.txt` file. */
  object Calendar {
    val ServiceId = 0
    val Monday = 1
    val Tuesday = 2
    val Wednesday = 3
    val Thursday = 4
    val Friday = 5
    val Saturday = 6
    val Sunday = 7
    val StartDate = 8
    val EndDate = 9

    /** Reads all the data from a file named `calendar.txt` in the resource folder. */
    fun data() = load("calendar.txt")
  }

  object CalendarDates {
    val ServiceId = 0
    val Date = 1
    val ExceptionType = 2

    /** Reads all the data from a file named `calendar_dates.txt` in the resource folder. */
    fun data() = load("calendar_dates.txt")
  }

  object FeedInfo {
    val FeedPublisherName = 0
    val FeedPublisherUrl = 1
    val FeedLang = 2
    val FeedStartDate = 3
    val FeedEndDate = 4
    val FeedVersion = 5

    /** Reads all the data from a file named `feed_info.txt` in the resource folder. */
    fun data() = load("feed_info.txt")
  }

  object Routes {
    val RouteId = 0
    val AgencyId = 1
    val RouteShortName = 2
    val RouteLongName = 3
    val RouteDesc = 4
    val RouteType = 5

    /** Reads all the data from a file named `routes.txt` in the resource folder. */
    fun data() = load("routes.txt")
  }

  object StopTimesTrain {
    val TripId = 0
    val ArrivalTime = 1
    val DepartureTime = 2
    val StopId = 3
    val StopSequence = 4
    val PickupType = 5
    val DropOffType = 6
    val ShapeDistTraveled = 7
    val AttributesCh = 8

    /** Reads all the data from a file named `stop_times.txt` in the resource folder. */
    fun data() = load("stop_times_20230606.txt")
  }

  object StopTimes {
    val TripId = 0
    val ArrivalTime = 1
    val DepartureTime = 2
    val StopId = 3
    val StopSequence = 4
    val PickupType = 5
    val DropOffType = 6

    /** Reads all the data from a file named `stop_times.txt` in the resource folder. */
    fun data() = load("stop_times.txt")
  }

  object Stops {
    val StopId = 0
    val StopName = 2
    val StopLat = 2
    val StopLon = 3
    val LocationType = 4
    val ParentStation = 5

    /** Reads all the data from a file named `stops.txt` in the resource folder. */
    fun data() = load("stops.txt")
  }

  object Transfers {
    val FromStopId = 0
    val ToStopId = 1
    val TransferType = 2
    val MinTransferTime = 3

    /** Reads all the data from a file named `transfers.txt` in the resource folder. */
    fun data() = load("transfers.txt")
  }

  object Trips {
    val RouteId = 0
    val ServiceId = 1
    val TripId = 2
    val TripHeadsign = 3
    val TripShortName = 4
    val DirectionId = 5

    /** Reads all the data from a file named `trips.txt` in the resource folder. */
    fun data() = load("trips.txt")
  }

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
    fun data() = load("mobilitat.csv", ';')
  }
}
