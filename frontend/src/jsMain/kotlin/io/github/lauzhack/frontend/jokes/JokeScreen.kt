package io.github.lauzhack.frontend.jokes

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.material.Button
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement

@Composable
fun JokeScreen(
    attrs: AttrBuilderContext<HTMLDivElement> = {},
    state: JokeScreenState,
) {
  val className = tailwind {
    flex()
    flexCol()
    itemsCenter()
    hScreen()
  }
  Div(
      attrs = {
        attrs()
        classes(className)
      },
  ) {
    H1 { Text("Welcome to the joke service !") }
    Span { Text(state.jokeText) }
    Button(attrs = { onClick { state.onRefreshClick() } }) { Text("Refresh") }
  }
}
