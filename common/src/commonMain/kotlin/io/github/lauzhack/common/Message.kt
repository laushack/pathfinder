package io.github.lauzhack.common

import kotlinx.serialization.Serializable

/**
 * A message that will be sent from one user to another.
 *
 * @property timestamp The time at which the message was sent.
 * @property sender The username of the user who sent the message.
 * @property content The content of the message.
 */
@Serializable
data class Message(
    val timestamp: Long,
    val sender: String,
    val content: String,
)
