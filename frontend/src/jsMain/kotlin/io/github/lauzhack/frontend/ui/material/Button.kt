package io.github.lauzhack.frontend.ui.material

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.Tokens.cffPink
import io.github.lauzhack.frontend.ui.Tokens.cffRed
import io.github.lauzhack.frontend.ui.Tokens.cffTransparent
import io.github.lauzhack.frontend.ui.Tokens.primary500
import io.github.lauzhack.frontend.ui.Tokens.primary600
import io.github.lauzhack.frontend.ui.Tokens.primary700
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

/** A Material-designed icon button. */
@Composable
fun IconButton(
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null,
) {
  Styled({ a, c -> CoreButton(a, c) }, attrs, content) {
    buttonStyle()
    roundedFull()
    p(4f)
    hover { bgColor("#F5F6FA") }
    transitionAll()
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
  textColor(cffRed)
  bgColor(cffTransparent)
  border(0f)
  hover { bgColor(cffPink) }
  active { bgColor(cffPink) }
  transitionAll()
}

private fun TailwindScope.buttonRaisedColorStyle() {
  bgColor(primary500)
  textColor(white)
  border(0f)
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
