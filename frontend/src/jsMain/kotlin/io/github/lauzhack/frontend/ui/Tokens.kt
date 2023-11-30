package io.github.lauzhack.frontend.ui

import io.github.lauzhack.frontend.ui.tailwind.*

object Tokens {

  fun TailwindScope.typeface() {
    fontSans()
  }

  fun TailwindScope.h1() {
    typeface()
    fontBold()
    textSizePxToRem(96f)
  }

  fun TailwindScope.h2() {
    typeface()
    fontBold()
    textSizePxToRem(60f)
  }

  fun TailwindScope.h3() {
    typeface()
    fontBold()
    textSizePxToRem(48f)
  }

  fun TailwindScope.h4() {
    typeface()
    fontBold()
    textSizePxToRem(34f)
  }

  fun TailwindScope.h5() {
    typeface()
    fontSemiBold()
    textSizePxToRem(24f)
  }

  fun TailwindScope.h6() {
    typeface()
    fontSemiBold()
    textSizePxToRem(20f)
  }

  fun TailwindScope.subtitle1() {
    typeface()
    fontSemiBold()
    textSizePxToRem(16f)
  }

  fun TailwindScope.subtitle2() {
    typeface()
    fontSemiBold()
    textSizePxToRem(14f)
  }

  fun TailwindScope.body1() {
    typeface()
    fontNormal()
    textSizePxToRem(16f)
  }

  fun TailwindScope.body2() {
    typeface()
    fontNormal()
    textSizePxToRem(14f)
  }

  fun TailwindScope.button() {
    typeface()
    fontSemiBold()
    textSizePxToRem(14f)
    uppercase()
  }

  fun TailwindScope.caption() {
    typeface()
    fontNormal()
    textSizePxToRem(12f)
  }

  fun TailwindScope.overline() {
    typeface()
    fontNormal()
    textSizePxToRem(10f)
    uppercase()
  }

  // Color tokens
  val primaryTransparent: String = "#0BD98600"
  val primaryAccent = "#0BD986"
  val primary100 = "#E5F9F2"
  val primary150 = "#CEF2E9"
  val primary300 = "#59C7A6"
  val primary500 = "#00BF80"
  val primary600 = "#00A67C"
  val primary700 = "#008967"
  val primary800 = "#006B59"
  val white = "#FFFFFF"
}
