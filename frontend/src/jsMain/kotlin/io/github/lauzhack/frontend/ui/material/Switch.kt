package io.github.lauzhack.frontend.ui.material

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.Tokens.primary100
import io.github.lauzhack.frontend.ui.Tokens.primary500
import io.github.lauzhack.frontend.ui.Tokens.white
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Button as CoreButton
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLButtonElement

/**
 * A Material-designed switch.
 *
 * @param checked Whether the switch is checked or not.
 */
@Composable
fun Switch(
    checked: Boolean,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
) {
  CoreButton(
      attrs = {
        inlineTailwind {
          minW(48f)
          bgColor(if (checked) primary500 else primary100)
          roundedFull()
          flex()
          flexRow()
          shadowInner()
          transitionAll()
          p(2f)
        }
        attrs?.invoke(this)
      },
  ) {
    Div(
        attrs = {
          inlineTailwind {
            shadow()
            w(28f)
            h(28f)
            bgColor(white)
            roundedFull()
            transitionAll()
            translateX(if (checked) 16f else 0f)
          }
        },
    )
  }
}
