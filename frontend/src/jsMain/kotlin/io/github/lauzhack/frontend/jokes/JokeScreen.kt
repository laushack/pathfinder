package io.github.lauzhack.frontend.jokes

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.tailwind.flex
import io.github.lauzhack.frontend.ui.tailwind.flexCol
import io.github.lauzhack.frontend.ui.tailwind.itemsCenter
import io.github.lauzhack.frontend.ui.tailwind.tailwind
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement

@Composable
fun JokeScreen(
    attrs: AttrBuilderContext<HTMLDivElement> = {},
    state: JokeScreenState,
) {
  Div(
      attrs = {
        attrs()
        tailwind {
          flex()
          flexCol()
          itemsCenter()
        }
      }) {
        H1 { Text("Welcome to the joke service !") }
        Span { Text(state.jokeText) }
        Button(attrs = { onClick { state.onRefreshClick() } }) { Text("Refresh") }
      }
}
