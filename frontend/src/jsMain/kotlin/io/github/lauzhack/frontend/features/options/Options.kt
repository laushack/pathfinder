package io.github.lauzhack.frontend.features.options

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.tailwind.flex
import io.github.lauzhack.frontend.ui.tailwind.flexCol
import io.github.lauzhack.frontend.ui.tailwind.inlineTailwind
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun Options(
    state: OptionsState,
) {
  Div(
      attrs = {
        inlineTailwind {
          flex()
          flexCol()
        }
      },
  ) {
    if (state.mode == OptionsState.Mode.Closed) {
      OptionsMinimized(state)
    } else {
      OptionsExpanded(state)
    }
  }
}

@Composable
fun OptionsMinimized(
    state: OptionsState,
) {
  Span { Text("Options minimized") }
  Span { Text(state.locationFrom ?: "No starting location") }
  Span { Text(state.locationTo ?: "No arrival location") }
  Span { Text(state.startTime ?: "No start time") }
  Span { Text(state.subscription ?: "No subscription") }
}

@Composable
fun OptionsExpanded(
    state: OptionsState,
) {
  Span { Text("Options expanded") }
  Span { Text(state.locationFrom ?: "No starting location") }
  Span { Text(state.locationTo ?: "No arrival location") }
  Span { Text(state.startTime ?: "No start time") }
  Span { Text(state.subscription ?: "No subscription") }
}
