package io.github.lauzhack.backend.utils.ktor

import io.ktor.serialization.*
import io.ktor.server.websocket.*
import io.ktor.util.reflect.*
import io.ktor.websocket.*

suspend inline fun <reified T> WebSocketServerSession.serializeToFrame(value: T): Frame {
  val converter = converter ?: throw IllegalStateException("No converter specified")
  val typeInfo = typeInfo<T>()
  val charset = call.request.headers.suitableCharset()
  return converter.serializeNullable(charset = charset, typeInfo = typeInfo, value = value)
}

suspend inline fun <reified T> WebSocketServerSession.deserializeFromFrame(
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
  error("Unexpected result from converter: $result")
}
