package io.github.lauzhack.common

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MessageTest {

  @Test
  fun messageContentStaysTheSameAfterSerialization() {
    val expected = Message(0, "sender", "content")

    val serialized = Json.encodeToString(expected)
    val actual = Json.decodeFromString<Message>(serialized)

    assertEquals(expected, actual)
  }
}
