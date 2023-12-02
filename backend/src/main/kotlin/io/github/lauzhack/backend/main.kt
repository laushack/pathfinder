@file:JvmName("Main")

package io.github.lauzhack.backend

import io.github.lauzhack.backend.data.Resources
import io.github.lauzhack.backend.jokes.HttpClientJokeService
import io.github.lauzhack.backend.jokes.JokeService
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*

/** The main entry point for the backend. */
fun main() {
  for (line in Resources.Mobilitat.data()) {
    println(line.contentToString())
  }
  val port = 8888
  val jokes = HttpClientJokeService()
  embeddedServer(CIO, port = port) { application(jokes) }.start(wait = true)
}

/** Configures the application. */
private fun Application.application(jokes: JokeService) {
  cors()
  contentNegotiation()
  http(jokes)
  sockets(jokes)
}

/** Configures the Cross-Origin Resource Sharing (CORS) plugin. */
private fun Application.cors() {
  install(CORS) {
    anyHost()
    allowCredentials = true
    allowNonSimpleContentTypes = true
  }
}

/** Configures the Content Negotiation plugin. */
private fun Application.contentNegotiation() {
  install(ContentNegotiation) { json() }
}

/** Configures the HTTP routing. */
private fun Application.http(service: JokeService) {
  routing {
    post("/refresh") {
      service.refresh()
      call.respond(HttpStatusCode.OK, "")
    }
  }
}

/** Configures the WebSocket routing. */
private fun Application.sockets(service: JokeService) {
  install(WebSockets) { contentConverter = KotlinxWebsocketSerializationConverter(DefaultJson) }
  routing { webSocket("/live") { service.latestJoke().collect { sendSerialized(it) } } }
}
