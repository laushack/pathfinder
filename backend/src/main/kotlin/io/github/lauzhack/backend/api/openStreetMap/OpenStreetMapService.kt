package io.github.lauzhack.backend.api.openAI

import io.github.lauzhack.backend.algorithm.Location
import io.github.lauzhack.backend.api.openStreetMap.OpenStreetMapRequest
import io.github.lauzhack.backend.api.openStreetMap.OpenStreetMapResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
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

  suspend fun search(request: OpenStreetMapRequest): OpenStreetMapResponse {
    return client.get("/search") {
      parameter("format", request.format)
      parameter("q", request.q)
    }.body()
  }

  /** Sends a prompt to the OpenStreetMap API and returns the Location, or null if not found. */
  suspend fun getLatLong(location: String): Location? {
    val request = OpenStreetMapRequest(q = location)
    val response = search(request)

    val lat = response.lat
    val lon = response.lon

    if (lat != null && lon != null) {
      return Location(lat.toDouble(), lon.toDouble())
    }

    return null
  }
}
