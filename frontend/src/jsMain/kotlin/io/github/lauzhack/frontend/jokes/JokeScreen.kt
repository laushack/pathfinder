package io.github.lauzhack.frontend.jokes

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.*

@Composable
fun JokeScreen(state: JokeScreenState) {
  Div {
    H1 { Text("Welcome to the joke service !") }
    Span { Text(state.jokeText) }
    Button(attrs = { onClick { state.onRefreshClick() } }) { Text("Refresh") }
  }
}
