@file:JvmName("Main")

package io.github.lauzhack.backend

import io.github.lauzhack.backend.algorithm.Algorithm
import io.github.lauzhack.backend.algorithm.Node
import io.github.lauzhack.backend.algorithm.Schedule
import io.github.lauzhack.backend.api.openAI.OpenAIService
import io.github.lauzhack.backend.features.session.Session
import io.github.lauzhack.backend.utils.ktor.deserializeFromFrame
import io.github.lauzhack.backend.utils.ktor.serializeToFrame
import io.github.lauzhack.common.api.BackendToUserMessage
import io.github.lauzhack.common.api.DefaultJsonSerializer
import io.github.lauzhack.common.api.UserToBackendMessage
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.selects.select

/** The main entry point for the backend. */
fun main() {
  val vlv = 8501116
  val loz = 8501120
  val stGallen = 8506302
  val startPoint = Node(vlv, 310, "")

  var startTime = System.currentTimeMillis()
  println("Building the schedule...")
  val schedule = Schedule.build()
  println("Schedule done! (took ${System.currentTimeMillis() - startTime}ms)")

  startTime = System.currentTimeMillis()
  println("Running the algorithm")
  val algorithm = Algorithm(schedule)
  val path = algorithm.run(startPoint, stGallen)
  println("Algorithm done! (took ${System.currentTimeMillis() - startTime}ms)")
  path?.forEachIndexed { i, n -> println("$i: $n") } ?: println("No path lol")

  val port = 8888
  embeddedServer(CIO, port = port) { application() }.start(wait = true)
}

/** Configures the application. */
private fun Application.application() {
  cors()
  contentNegotiation()
  sockets()
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
  install(ContentNegotiation) { json(DefaultJsonSerializer) }
}

/** Configures the WebSocket routing. */
private fun Application.sockets() {
  install(WebSockets) {
    contentConverter = KotlinxWebsocketSerializationConverter(DefaultJsonSerializer)
  }
  routing {
    webSocket("/live") {
      val toSend = mutableListOf<BackendToUserMessage>()
      val session = Session(toSend::add, OpenAIService())
      var keepGoing = true
      while (keepGoing) {
        if (toSend.isNotEmpty()) {
          val frame = serializeToFrame(toSend.first())
          select<Unit> {
            outgoing.onSend(frame) { toSend.removeFirst() }
            incoming.onReceiveCatching { message ->
              message
                  .onSuccess { session.process(deserializeFromFrame(it)) }
                  .onFailure { keepGoing = false }
                  .onClosed { keepGoing = false }
            }
          }
        } else {
          val message = deserializeFromFrame<UserToBackendMessage>(incoming.receive())
          session.process(message)
        }
      }
    }
  }
}
