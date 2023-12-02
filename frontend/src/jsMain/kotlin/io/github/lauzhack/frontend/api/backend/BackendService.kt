package io.github.lauzhack.frontend.api.backend

import androidx.compose.runtime.*
import io.github.lauzhack.common.api.*
import io.github.lauzhack.frontend.utils.ktor.deserializeFromFrame
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import kotlin.js.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

/** The [HttpClient] used to communicate with the backend. */
val HttpClient = HttpClient {
  install(ContentNegotiation) { json() }
  install(WebSockets) {
    contentConverter = KotlinxWebsocketSerializationConverter(DefaultJsonSerializer)
  }

  defaultRequest {
    host = "localhost"
    port = 8888
  }
}

/** The composition local to access the backend. */
val LocalBackendService =
    staticCompositionLocalOf<BackendService> { error("Backend not provided.") }

/** Returns the [BackendService] used to communicate with the backend. */
@Composable
private fun rememberBackendService(): BackendService {
  val scope = rememberCoroutineScope()
  return remember(scope) { BackendService(scope) }
}

/** Provides the [BackendService] used to communicate with the backend. */
@Composable
fun ProvideBackendService(content: @Composable () -> Unit) {
  val backendService = rememberBackendService()
  CompositionLocalProvider(LocalBackendService provides backendService, content = content)
}

/** A service to communicate with the backend. */
class BackendService(
    scope: CoroutineScope,
    private val client: HttpClient = HttpClient,
) {

  /** The conversation with the assistant. */
  var conversation: List<AssistantToUserMessage> by mutableStateOf(emptyList())
    private set

  /** The planning options with the assistant. */
  var planningOptions: PlanningOptions by mutableStateOf(PlanningOptions())
    private set

  /** A list of all the messages to send to the backend. */
  private val toSend = Channel<UserToBackendMessage>(Channel.UNLIMITED)

  /** Sends the given [text] message to the backend, in the chat */
  fun sendChatMessage(text: String) {
    conversation = conversation + AssistantToUserMessage(role = AssistantRole.User, text = text)
    toSend.trySend(UserToAssistantMessage(text))
  }

  init {
    scope.launch {
      while (true) {
        client.webSocket("/live") {
          while (true) {
            select {
              toSend.onReceive { msg -> sendSerialized<UserToBackendMessage>(msg) }
              incoming.onReceive {
                when (val msg = deserializeFromFrame<BackendToUserMessage>(it)) {
                  is AssistantToUserConversation -> conversation = msg.messages
                  is AssistantToUserSetPlanning -> planningOptions = msg.planningOptions
                }
              }
            }
          }
        }
      }
    }
  }
}
