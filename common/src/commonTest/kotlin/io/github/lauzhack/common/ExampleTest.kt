package io.github.lauzhack.common

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class ExampleTest {

  @Test
  fun doubleTest() = runTest {
    val expected = 4
    val input = 2

    val actual = doubleAfterDelay(input)

    assertEquals(expected, actual)
  }
}
