package io.github.lauzhack.frontend.features.options

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.Tokens.black
import io.github.lauzhack.frontend.ui.Tokens.body1
import io.github.lauzhack.frontend.ui.Tokens.caption
import io.github.lauzhack.frontend.ui.Tokens.subtitle1
import io.github.lauzhack.frontend.ui.Tokens.white
import io.github.lauzhack.frontend.ui.material.*
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
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
  Div(
      attrs = {
        inlineTailwind {
          flex()
          flexRow()
          itemsStart()
          gap(16f)
        }
      },
  ) {
    Div(
        attrs = {
          inlineTailwind {
            flex()
            flexRow()
            flexWrap()
            itemsCenter()
            gap(8f)
            grow()
          }
        },
    ) {
      state.locationFrom?.let { OptionPill(Icons.TripStartIcon, it) }
      state.locationTo?.let { OptionPill(Icons.TripEndIcon, it) }
      state.startTime?.let { OptionPill(Icons.TripTimeIcon, it) }
      state.subscription?.let { OptionPill(Icons.TripSubscription, it) }
    }
    Div(
        attrs = {
          inlineTailwind {
            flex()
            flexRow()
            bgColor(white)
            shadow()
            roundedLg()
          }
        },
    ) {
      OutlinedButton(
          attrs = {
            onClick { state.onToggleModeClick() }
            inlineTailwind { gap(8f) }
          },
      ) {
        Icon(Icons.Edit)
        Text("Edit")
      }
    }
  }
}

@Composable
private fun OptionPill(
    icon: IconPath,
    text: String,
) {
  Div(
      attrs = {
        inlineTailwind {
          bgColor(white)
          flex()
          flexRow()
          itemsCenter()
          gap(8f)
          roundedFull()
          py(4f)
          px(12f)
          caption()
          fontMedium()
          shadow()
        }
      },
  ) {
    Icon(icon)
    Text(text)
  }
}

@Composable
fun OptionsExpanded(
    state: OptionsState,
) {
  Div(
      attrs = {
        inlineTailwind {
          flex()
          flexCol()
          gap(16f)
          p(16f)

          bgColor(white)
          shadow()
          roundedLg()
          body1()
        }
      },
  ) {
    Span(attrs = { inlineTailwind { subtitle1() } }) { Text("Options") }
    OptionsInput(
        icon = Icons.TripStartIcon,
        placeholder = "EPFL, ...",
        value = state.locationFromInput,
        onValueChange = { state.locationFromInput = it },
    )
    OptionsInput(
        icon = Icons.TripEndIcon,
        placeholder = "Geneva, ...",
        value = state.locationToInput,
        onValueChange = { state.locationToInput = it },
    )
    OptionsInput(
        icon = Icons.TripTimeIcon,
        placeholder = "18:00, ...",
        value = state.startTimeInput,
        onValueChange = { state.startTimeInput = it },
    )
    OptionsInput(
        icon = Icons.TripSubscription,
        placeholder = "Half-fare, ...",
        value = state.subscriptionInput,
        onValueChange = { state.subscriptionInput = it },
    )
    Div(
        attrs = {
          inlineTailwind {
            flex()
            flexRow()
            gap(16f)
            itemsCenter()
            selfEnd()
          }
        },
    ) {
      OutlinedButton(
          attrs = {
            onClick { state.onToggleModeClick() }
            inlineTailwind { textColor(black) }
          },
      ) {
        Text("Cancel")
      }
      OutlinedButton(attrs = { onClick { state.onSave() } }) { Text("Save") }
    }
  }
}

@Composable
private fun OptionsInput(
    icon: IconPath,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
  Span(
      attrs = {
        inlineTailwind {
          flex()
          flexRow()
          itemsCenter()
          gap(16f)
        }
      },
  ) {
    Icon(icon)
    Input(
        type = InputType.Text,
        attrs = {
          inlineTailwind { grow() }
          placeholder(placeholder)
          value(value)
          onInput { onValueChange(it.value) }
        },
    )
  }
}
