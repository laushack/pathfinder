package io.github.lauzhack.frontend.utils.ktor

import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.*
import io.ktor.util.reflect.*
import io.ktor.websocket.*

suspend inline fun <reified T> DefaultClientWebSocketSession.deserializeFromFrame(
    frame: Frame,
): T {
  val converter = converter ?: throw IllegalStateException("No converter specified")
  val typeInfo = typeInfo<T>()
  val charset = call.request.headers.suitableCharset()
  if (!converter.isApplicable(frame)) {
    throw WebsocketDeserializeException(
        "Converter doesn't support frame type ${frame.frameType.name}", frame = frame)
  }

  val result = converter.deserialize(charset = charset, typeInfo = typeInfo, content = frame)

  when {
    typeInfo.type.isInstance(result) -> return result as T
    result == null -> {
      if (typeInfo.kotlinType?.isMarkedNullable == true) return null as T
      throw WebsocketDeserializeException("Frame has null content", frame = frame)
    }
  }

  throw WebsocketDeserializeException(
      "Can't deserialize value: expected value of type ${typeInfo.type.simpleName}," +
          " got ${result!!::class.simpleName}",
      frame = frame)
}
