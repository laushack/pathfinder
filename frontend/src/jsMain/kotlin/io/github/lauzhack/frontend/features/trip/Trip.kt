package io.github.lauzhack.frontend.features.trip

import androidx.compose.runtime.*
import io.github.lauzhack.common.api.Trip
import io.github.lauzhack.common.api.TripStop
import io.github.lauzhack.common.api.compact
import io.github.lauzhack.frontend.ui.Tokens.blue
import io.github.lauzhack.frontend.ui.Tokens.body1
import io.github.lauzhack.frontend.ui.Tokens.body2
import io.github.lauzhack.frontend.ui.Tokens.caption
import io.github.lauzhack.frontend.ui.Tokens.cffRed
import io.github.lauzhack.frontend.ui.Tokens.cffRedVeryLight
import io.github.lauzhack.frontend.ui.Tokens.h5
import io.github.lauzhack.frontend.ui.Tokens.white
import io.github.lauzhack.frontend.ui.material.Icon
import io.github.lauzhack.frontend.ui.material.IconButton
import io.github.lauzhack.frontend.ui.material.Icons.ChevronDown
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLSpanElement

// I want to get form Divonne to Zurich at 8am

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
          overflowYScroll()
        }
        attrs?.invoke(this)
      },
  ) {
    TripHelp()
    H5(attrs = { inlineTailwind { h5() } }) { Text("Suggested trip:") }
    trip.compact().subTrips.forEach { SubTrip(it) }
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

@Composable
private fun SubTrip(
    trip: Trip,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
) {
  val from = trip.stops.first()
  val to = trip.stops.last()
  val intermediaryStops = trip.stops.drop(1).dropLast(1)
  var open by remember { mutableStateOf(false) }
  Div(
      attrs = {
        inlineTailwind {
          flex()
          flexCol()
          px(8f)
          py(8f)
          border(2f)
          borderColor(if (from.name == "Start" || to.name == "End") blue else cffRed)
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
    if (from.departureTime != null || to.arrivalTime != null) {
      Span(
          attrs = { inlineTailwind { body2() } },
      ) {
        Text("${from.departureTime ?: "Start"} → ${to.arrivalTime ?: "End"}")
      }
    }
    if (intermediaryStops.isNotEmpty()) {
      IconButton(
          attrs = { onClick { open = !open } },
      ) {
        Icon(
            attrs = {
              inlineTailwind {
                rotate(if (open) 180f else 0f)
                transitionAll()
              }
            },
            path = ChevronDown,
        )
      }
    }
    if (intermediaryStops.isNotEmpty() && open) {
      Div(
          attrs = {
            inlineTailwind {
              flex()
              flexCol()
              py(8f)
              caption()
            }
          },
      ) {
        intermediaryStops.forEach { stop ->
          key(stop) {
            Span {
              Span { Text("• ") }
              Span { Text(stop.arrivalTime ?: "") }
              Span { Text(" → ") }
              Span(attrs = { inlineTailwind { fontSemiBold() } }) { Text(stop.name) }
              Span { Text(" → ") }
              Span { Text((stop.departureTime ?: "")) }
            }
          }
        }
      }
    }
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
          borderColor(if (from.name == "Start" || to.name == "End") blue else cffRed)
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
    if (from.departureTime != null || to.arrivalTime != null) {
      Span(
          attrs = { inlineTailwind { body2() } },
      ) {
        Text("${from.departureTime ?: "Start"} → ${to.arrivalTime ?: "End"}")
      }
    }
  }
}
