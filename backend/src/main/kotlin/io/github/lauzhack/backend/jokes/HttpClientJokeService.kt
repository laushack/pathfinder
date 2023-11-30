package io.github.lauzhack.backend.jokes

import io.github.lauzhack.common.DadJoke
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

/**
 * An implementation of [JokeService] which uses an HTTP client to fetch jokes from the
 * [icanhazdadjoke.com](https://icanhazdadjoke.com) API.
 */
class HttpClientJokeService(
    private val httpClient: HttpClient =
        HttpClient(CIO) {
          defaultRequest {
            url {
              protocol = URLProtocol.HTTPS
              host = "icanhazdadjoke.com"
            }
          }
          install(ContentNegotiation) { json() }
        }
) : JokeService {

  private val latest = MutableStateFlow<DadJoke?>(null)

  override suspend fun refresh() {
    try {
      latest.value = httpClient.get("/").body<DadJoke>()
    } catch (ignored: Throwable) {}
  }

  override fun latestJoke(): Flow<DadJoke> = latest.filterNotNull()
}
