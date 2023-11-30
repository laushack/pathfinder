package io.github.lauzhack.frontend.jokes

import androidx.compose.runtime.*
import io.github.lauzhack.common.DadJoke
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

/** Returns a [JokeScreenState] which can be used to display dad jokes. */
@Composable
fun rememberJokeScreenState(): JokeScreenState {
  val scope = rememberCoroutineScope()
  return remember(scope) { ActualJokeScreenState(scope) }
}

/** An implementation of [JokeScreenState] which fetches data remotely. */
private class ActualJokeScreenState(private val scope: CoroutineScope) : JokeScreenState {

  private val httpClient = HttpClient {
    install(ContentNegotiation) { json() }
    install(WebSockets) { contentConverter = KotlinxWebsocketSerializationConverter(Json) }

    defaultRequest {
      host = "localhost"
      port = 8888
    }
  }

  override var jokeText: String by mutableStateOf("Loading...")
    private set

  override fun onRefreshClick() {
    scope.launch { httpClient.post("/refresh") }
  }

  init {
    scope.launch {
      httpClient.webSocket("/live") {
        while (true) {
          val joke = receiveDeserialized<DadJoke>()
          jokeText = joke.joke
        }
      }
    }
  }
}
