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
}
