package io.github.lauzhack.frontend

import androidx.compose.runtime.*
import io.github.lauzhack.frontend.api.backend.LocalBackendService
import io.github.lauzhack.frontend.api.backend.ProvideBackendService
import io.github.lauzhack.frontend.features.assistant.ChatFloatingWindow
import io.github.lauzhack.frontend.features.assistant.rememberChatState
import io.github.lauzhack.frontend.features.options.Options
import io.github.lauzhack.frontend.features.options.rememberOptionsState
import io.github.lauzhack.frontend.features.trip.Trip
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
                flexRow()
                hFull()
                gap(16f)
                px(32f)
                py(16f)
              }
            },
        ) {
          val currentTrip = LocalBackendService.current.trip
          Div(
              attrs = {
                inlineTailwind {
                  flex()
                  flexCol()
                  overflowYScroll()
                  gap(8f)
                  maxWLg()
                  wFull()
                  hFull()
                }
              },
          ) {
            ChatFloatingWindow(rememberChatState())
            Options(rememberOptionsState())
          }
          if (currentTrip != null) {
            Trip(
                trip = currentTrip,
                attrs = {
                  inlineTailwind {
                    maxWSm()
                    hFull()
                  }
                },
            )
          }
        }
      }
    }
  }
}
