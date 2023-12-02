package io.github.lauzhack.frontend.ui.material

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.Tokens.primary100
import io.github.lauzhack.frontend.ui.Tokens.primary150
import io.github.lauzhack.frontend.ui.Tokens.primary500
import io.github.lauzhack.frontend.ui.Tokens.primary600
import io.github.lauzhack.frontend.ui.Tokens.primary700
import io.github.lauzhack.frontend.ui.Tokens.primaryTransparent
import io.github.lauzhack.frontend.ui.Tokens.white
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Button as CoreButton
import org.jetbrains.compose.web.dom.ContentBuilder
import org.w3c.dom.HTMLButtonElement

/** A Material-designed button. */
@Composable
fun OutlinedButton(
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null
) {
  Styled({ a, c -> CoreButton(a, c) }, attrs, content) {
    buttonStyle()
    buttonTextStyle()
    buttonSmallLayoutStyle()
    buttonFlatColorStyle()
  }
}

/** A Material-designed button with a raised appearance. */
@Composable
fun Button(
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null
) {
  Styled({ a, c -> CoreButton(a, c) }, attrs, content) {
    buttonStyle()
    buttonTextStyle()
    buttonSmallLayoutStyle()
    buttonRaisedColorStyle()
  }
}

private fun TailwindScope.buttonStyle() {
  cursorAuto()
  selectNone()
  flex()
  flexRow()
  justifyCenter()
  itemsCenter()
  spaceX(8f)
  appearanceNone()
}

private fun InlineTailwindScope.buttonSmallLayoutStyle() {
  roundedLg()
  py(8f)
  px(8f)
  minW(48f)
}

private fun InlineTailwindScope.buttonTextStyle() {
  uppercase()
  fontSemiBold()
  fontSans()
  textSm()
}

private fun TailwindScope.buttonFlatColorStyle() {
  textColor(primary500)
  bgColor(primaryTransparent)
  border(0)
  hover { bgColor(primary100) }
  active { bgColor(primary150) }
  transitionAll()
}

private fun TailwindScope.buttonRaisedColorStyle() {
  bgColor(primary500)
  textColor(white)
  border(0)
  dropShadow()
  hover {
    bgColor(primary600)
    dropShadowLg()
  }
  active {
    bgColor(primary700)
    dropShadowXl()
  }
  transitionAll()
}