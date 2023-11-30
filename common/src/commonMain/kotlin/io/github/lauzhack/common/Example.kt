package io.github.lauzhack.common

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay

/** Doubles the value of the given [value] after a delay of 1 second. */
suspend fun doubleAfterDelay(value: Int, amount: Duration = 1.seconds): Int {
  delay(amount)
  return value * 2
}
