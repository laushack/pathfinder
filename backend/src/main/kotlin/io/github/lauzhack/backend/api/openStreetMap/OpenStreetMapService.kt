package io.github.lauzhack.backend.api.openAI

import io.github.lauzhack.backend.algorithm.Location
import io.github.lauzhack.backend.api.openStreetMap.OpenStreetMapRequest
import io.github.lauzhack.backend.api.openStreetMap.OpenStreetMapResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

/** Creates an HTTP client for the OpenStreetMap API. */
private fun httpClient() =
    HttpClient(CIO) {
      defaultRequest {
        url {
          protocol = URLProtocol.HTTPS
          host = "nominatim.openstreetmap.org"
        }
      }
      install(ContentNegotiation) { json() }
    }

/** A service for the OpenAI API. */
class OpenStreetMapService() {
  private val client = httpClient()

  fun printSomething() {
    println("Hello world!")
  }

  suspend fun search(request: OpenStreetMapRequest): List<OpenStreetMapResponse> {
    return client.get("/search?format=${request.format}&q=${request.q}").body()
  }

  /** Sends a prompt to the OpenStreetMap API and returns the Location, or null if not found. */
  suspend fun getLatLong(location: String): Location? {
    val request = OpenStreetMapRequest(q = location)

    search(request).firstOrNull()?.let {
      return Location(
          lat = it.lat.toDouble(),
          lon = it.lon.toDouble(),
      )
    }

    println("$location not found")
    return null
  }
}
