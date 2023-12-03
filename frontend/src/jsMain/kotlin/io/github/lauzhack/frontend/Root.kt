package io.github.lauzhack.frontend

import androidx.compose.runtime.*
import io.github.lauzhack.frontend.api.backend.ProvideBackendService
import io.github.lauzhack.frontend.features.assistant.ChatFloatingWindow
import io.github.lauzhack.frontend.features.assistant.rememberChatState
import io.github.lauzhack.frontend.features.options.Options
import io.github.lauzhack.frontend.features.options.rememberOptionsState
import io.github.lauzhack.frontend.ui.tailwind.*
import mapbox.compose.MapBox
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLBodyElement

/** The root of the application. */
@Composable
fun DOMScope<HTMLBodyElement>.Root() {
  ProvideTailwindStyles {
    ProvideBackendService {
      // Use absolute and relative positions to overlay MapBox behind the chat window.
      Div(
          attrs = {
            inlineTailwind {
              relative()
              wFull()
              hScreen()
            }
          },
      ) {
        MapBox(
            attrs = {
              inlineTailwind {
                absolute()
                wFull()
                hFull()
              }
            },
        )
        Div(
            attrs = {
              inlineTailwind {
                absolute()
                grow()
                overflowYScroll()
                flex()
                flexCol()
                maxWLg()
                wFull()
                hFull()
                gap(8f)
                px(32f)
                py(16f)
              }
            },
        ) {
          // Text(LocalBackendService.current.trip)
          ChatFloatingWindow(rememberChatState())
          Options(rememberOptionsState())
        }
      }
    }
  }
}
