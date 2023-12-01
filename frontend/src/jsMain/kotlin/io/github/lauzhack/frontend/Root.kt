package io.github.lauzhack.frontend

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.jokes.JokeScreen
import io.github.lauzhack.frontend.jokes.rememberJokeScreenState
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLBodyElement

/** The root of the application. */
@Composable
fun DOMScope<HTMLBodyElement>.Root() {
  ProvideTailwindStyles {
    val className = tailwind {
      flex()
      flexCol()
      hScreen()
      itemsCenter()
      justifyCenter()
    }
    Div(attrs = { classes(className) }) {
      val state = rememberJokeScreenState()
      JokeScreen(state = state)
    }
  }
}
