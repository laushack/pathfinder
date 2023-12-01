package io.github.lauzhack.frontend

import androidx.compose.runtime.*
import io.github.lauzhack.frontend.jokes.JokeScreen
import io.github.lauzhack.frontend.jokes.rememberJokeScreenState
import io.github.lauzhack.frontend.ui.tailwind.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLBodyElement

/** The root of the application. */
@Composable
fun DOMScope<HTMLBodyElement>.Root() {
  ProvideTailwindStyles {
    Div(
        attrs = {
          inlineTailwind {
            flex()
            flexCol()
            hScreen()
            itemsCenter()
            justifyCenter()
          }
        },
    ) {
      val state = rememberJokeScreenState()
      var translationX by remember { mutableStateOf(0f) }
      var translationY by remember { mutableStateOf(0f) }
      LaunchedEffect(Unit) {
        while (true) {
          delay(250)
          translationX = (-100..100).random().toFloat()
          translationY = (-100..100).random().toFloat()
        }
      }
      JokeScreen(
          attrs = {
            inlineTailwind {
              translateXY(translationX, translationY)
              transitionAll(duration = 300)
            }
          },
          state = state,
      )
    }
  }
}
