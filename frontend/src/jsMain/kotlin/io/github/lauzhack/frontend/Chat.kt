package io.github.lauzhack.frontend

import androidx.compose.runtime.*
import io.github.lauzhack.common.api.*
import io.github.lauzhack.frontend.ui.material.OutlinedButton
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
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

interface Chat {

  val conversation: List<String>

  var input: String

  fun send()
}

class ChatImpl(scope: CoroutineScope) : Chat {

  private val httpClient = HttpClient {
    install(ContentNegotiation) { json() }
    install(WebSockets) {
      contentConverter = KotlinxWebsocketSerializationConverter(DefaultJsonSerializer)
    }

    defaultRequest {
      host = "localhost"
      port = 8888
    }
  }

  private val messages = mutableStateListOf<String>()

  private val queue = Channel<String>(Channel.UNLIMITED)

  override val conversation: List<String>
    get() = messages

  override var input: String by mutableStateOf("")

  override fun send() {
    queue.trySend(input)
    input = ""
  }

  init {
    scope.launch {
      httpClient.webSocket("/live") {
        while (true) {
          select {
            queue.onReceive { message ->
              sendSerialized<UserToBackendMessage>(UserToAssistantMessage(message))
            }
            incoming.onReceive { message ->
              val response = deserializeFromFrame<BackendToUserMessage>(message)
              when (response) {
                is AssistantToUserMessage -> messages.add(response.text)
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun rememberChatState(): Chat {
  val scope = rememberCoroutineScope()
  return remember(scope) { ChatImpl(scope) }
}

@Composable
fun ChatScreen(chat: Chat) {
  Div {
    chat.conversation.forEach { message -> Span { Text(message) } }
    Input(
        InputType.Text,
        attrs = {
          value(chat.input)
          onInput { chat.input = it.value }
        },
    )
    OutlinedButton(attrs = { onClick { chat.send() } }) { Text("Send") }
  }
}
