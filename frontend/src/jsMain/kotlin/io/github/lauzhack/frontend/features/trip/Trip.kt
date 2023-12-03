package io.github.lauzhack.frontend.features.trip

import androidx.compose.runtime.Composable
import io.github.lauzhack.common.api.Trip
import io.github.lauzhack.common.api.TripStop
import io.github.lauzhack.frontend.ui.Tokens.body1
import io.github.lauzhack.frontend.ui.Tokens.body2
import io.github.lauzhack.frontend.ui.Tokens.cffRed
import io.github.lauzhack.frontend.ui.Tokens.cffRedVeryLight
import io.github.lauzhack.frontend.ui.Tokens.h5
import io.github.lauzhack.frontend.ui.Tokens.white
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLSpanElement

/** Displays a trip, by showing the different steps. */
@Composable
fun Trip(trip: Trip, attrs: AttrBuilderContext<HTMLDivElement>? = null) {
  Div(
      attrs = {
        inlineTailwind {
          shadow()
          roundedXl()
          bgColor(white)
          flex()
          flexCol()
          p(16f)
          gap(16f)
        }
        attrs?.invoke(this)
      },
  ) {
    TripHelp()
    H5(attrs = { inlineTailwind { h5() } }) { Text("Suggested trip:") }
    trip.stops.zipWithNext().forEach { (from, to) -> TripStep(from, to) }
  }
}

/** Displays some help information at the top of a trip. */
@Composable
private fun TripHelp(attrs: AttrBuilderContext<HTMLSpanElement>? = null) {
  Span(
      attrs = {
        inlineTailwind {
          body1()
          bgColor(cffRedVeryLight)
          border(2f)
          borderDotted()
          borderColor(cffRed)
          roundedLg()
          p(8f)
        }
        attrs?.invoke(this)
      },
  ) {
    Text(
        "I now have enough information to suggest a trip! Keep chatting with the assistant to refine your preferences.")
  }
}

/** Displays a step of a trip. */
@Composable
private fun TripStep(
    from: TripStop,
    to: TripStop,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
) {
  Div(
      attrs = {
        inlineTailwind {
          flex()
          flexCol()
          px(8f)
          py(8f)
          border(2f)
          borderColor(cffRed)
          roundedLg()
        }
        attrs?.invoke(this)
      },
  ) {
    Span(
        attrs = {
          inlineTailwind {
            body1()
            fontBold()
          }
        },
    ) {
      Text("${from.name} → ${to.name}")
    }
    Span(
        attrs = { inlineTailwind { body2() } },
    ) {
      Text("${from.departureTime ?: "Start"} → ${to.arrivalTime ?: "End"}")
    }
  }
}
