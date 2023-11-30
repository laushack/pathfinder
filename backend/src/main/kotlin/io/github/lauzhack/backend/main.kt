@file:JvmName("Main")

package io.github.lauzhack.backend

import io.github.lauzhack.common.Message
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/** A common application state.. */
class State {

  /** A [Mutex] to protect the counter from concurrent access. */
  private val mutex = Mutex()

  /** The number of requests that have been made to the server. */
  private var counter = 0

  /** Returns a unique number for each call. */
  suspend fun next(): Int = mutex.withLock { counter++ }
}

/** The main entry point for the backend. */
fun main() {
  val port = 8888
  val state = State()
  embeddedServer(CIO, port = port) { application(state) }.start(wait = true)
}

/** Configures the application. */
private fun Application.application(state: State) {
  http(state)
  sockets(state)
}

/** Configures the HTTP routing. */
private fun Application.http(state: State) {
  routing {
    get("/test") {
      // Send a unique identifier to the client.
      val now = System.now().epochSeconds
      val message = Message(now, "server", "Request number ${state.next()}")
      val encoded = Json.encodeToString(message)
      call.respondText(encoded)
    }
  }
}

/** Configures the WebSocket routing. */
private fun Application.sockets(state: State) {
  install(WebSockets)
  routing {
    webSocket("example") {
      // Send incrementing identifiers to the client.
      while (true) {
        val now = System.now().epochSeconds
        val message = Message(now, "server", "Msg ${state.next()}")
        val encoded = Json.encodeToString(message)
        send(encoded)
      }
    }
  }
}
