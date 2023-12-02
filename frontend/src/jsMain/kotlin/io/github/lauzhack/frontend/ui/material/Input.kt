package io.github.lauzhack.frontend.ui.material

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.Tokens.primary500
import io.github.lauzhack.frontend.ui.Tokens.subtitle1
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.builders.InputAttrsScope
import org.jetbrains.compose.web.dom.Input as CoreInput

/**
 * A Material-designed input.
 *
 * @param type The type of the input.
 * @param attrs The attributes of the input.
 */
@Composable
fun <K> Input(
    type: InputType<K>,
    attrs: InputAttrsScope<K>.() -> Unit = {},
) {
  val className = tailwind {
    textColor("#4a5568")
    bgColor("#edf2f7")
    subtitle1()
    py(12f)
    px(16f)
    roundedLg()
    accentColor(primary500)
    active { outlineColor(primary500) }
    focus { outlineColor(primary500) }
    transitionAll()
  }
  CoreInput(
      type = type,
      attrs = {
        classes(className)
        attrs()
      },
  )
}
