package io.github.lauzhack.frontend.ui

import io.github.lauzhack.frontend.ui.tailwind.*

object Tokens {

  fun InlineTailwindScope.typeface() {
    fontSans()
  }

  fun InlineTailwindScope.h1() {
    typeface()
    fontBold()
    textSizePxToRem(96f)
  }

  fun InlineTailwindScope.h2() {
    typeface()
    fontBold()
    textSizePxToRem(60f)
  }

  fun InlineTailwindScope.h3() {
    typeface()
    fontBold()
    textSizePxToRem(48f)
  }

  fun InlineTailwindScope.h4() {
    typeface()
    fontBold()
    textSizePxToRem(34f)
  }

  fun InlineTailwindScope.h5() {
    typeface()
    fontSemiBold()
    textSizePxToRem(24f)
  }

  fun InlineTailwindScope.h6() {
    typeface()
    fontSemiBold()
    textSizePxToRem(20f)
  }

  fun InlineTailwindScope.subtitle1() {
    typeface()
    fontSemiBold()
    textSizePxToRem(16f)
  }

  fun InlineTailwindScope.subtitle2() {
    typeface()
    fontSemiBold()
    textSizePxToRem(14f)
  }

  fun InlineTailwindScope.body1() {
    typeface()
    fontNormal()
    textSizePxToRem(16f)
  }

  fun InlineTailwindScope.body2() {
    typeface()
    fontNormal()
    textSizePxToRem(14f)
  }

  fun InlineTailwindScope.button() {
    typeface()
    fontSemiBold()
    textSizePxToRem(14f)
    uppercase()
  }

  fun InlineTailwindScope.caption() {
    typeface()
    fontNormal()
    textSizePxToRem(12f)
  }

  fun InlineTailwindScope.overline() {
    typeface()
    fontNormal()
    textSizePxToRem(10f)
    uppercase()
  }

  // Color tokens
  val cffRed = "#EB0000"
  val cffPinkLight = "#FFBCD9"
  val cffPinkLightVeryLight = "#FFBCD940"
  val cffPink = "#FF85A2"
  val cffPinkVeryLight = "#FF85A240"
  val cffPinkDark = "#C44F6F"

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
