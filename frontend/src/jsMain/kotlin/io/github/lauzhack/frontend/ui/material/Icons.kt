package io.github.lauzhack.frontend.ui.material

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.tailwind.fillCurrent
import io.github.lauzhack.frontend.ui.tailwind.h
import io.github.lauzhack.frontend.ui.tailwind.inlineTailwind
import io.github.lauzhack.frontend.ui.tailwind.w
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg
import org.w3c.dom.svg.SVGElement

/**
 * An icon.
 *
 * @param path The path of the icon.
 * @param attrs The attributes of the icon.
 */
@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun Icon(
    path: IconPath,
    attrs: AttrBuilderContext<SVGElement>? = null,
) {
  Svg(
      viewBox = "0 0 24 24",
      attrs = {
        inlineTailwind {
          w(24f)
          h(24f)
          fillCurrent()
        }
        attrs?.invoke(this)
      },
  ) {
    Path(d = path.d)
  }
}

/** The path of an icon. */
value class IconPath(val d: String)

/** Paths of the different icons used in our application. */
object Icons {
  val SendIcon = IconPath("M3 20V4L22 12M5 17L16.85 12L5 7V10.5L11 12L5 13.5M5 17V7 13.5Z")
  val TripStartIcon =
      IconPath(
          "M23,12L19,16V13H6.83C6.42,14.17 5.31,15 4,15A3,3 0 0,1 1,12A3,3 0 0,1 4,9C5.31,9 6.42,9.83 6.83,11H19V8L23,12Z")
  val TripEndIcon =
      IconPath(
          "M20,9C18.69,9 17.58,9.83 17.17,11H2V13H17.17C17.58,14.17 18.69,15 20,15A3,3 0 0,0 23,12A3,3 0 0,0 20,9Z")
  val TripTimeIcon =
      IconPath(
          "M12,20A8,8 0 0,0 20,12A8,8 0 0,0 12,4A8,8 0 0,0 4,12A8,8 0 0,0 12,20M12,2A10,10 0 0,1 22,12A10,10 0 0,1 12,22C6.47,22 2,17.5 2,12A10,10 0 0,1 12,2M12.5,7V12.25L17,14.92L16.25,16.15L11,13V7H12.5Z")
  val TripSubscription =
      IconPath(
          "M12,15H10V13H12V15M18,15H14V13H18V15M8,11H6V9H8V11M18,11H10V9H18V11M20,20H4A2,2 0 0,1 2,18V6A2,2 0 0,1 4,4H20A2,2 0 0,1 22,6V18A2,2 0 0,1 20,20M4,6V18H20V6H4Z")
  val Edit =
      IconPath(
          "M20.71,7.04C21.1,6.65 21.1,6 20.71,5.63L18.37,3.29C18,2.9 17.35,2.9 16.96,3.29L15.12,5.12L18.87,8.87M3,17.25V21H6.75L17.81,9.93L14.06,6.18L3,17.25Z")
}
