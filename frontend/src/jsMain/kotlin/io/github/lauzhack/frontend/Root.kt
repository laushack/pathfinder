package io.github.lauzhack.frontend

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.jokes.JokeScreen
import io.github.lauzhack.frontend.jokes.rememberJokeScreenState
import org.jetbrains.compose.web.dom.DOMScope
import org.w3c.dom.HTMLBodyElement

/** The root of the application. */
@Composable
fun DOMScope<HTMLBodyElement>.Root() {
  val state = rememberJokeScreenState()
  JokeScreen(state)
}
