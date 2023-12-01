package io.github.lauzhack.frontend.ui.tailwind

// FONT SIZE (https://tailwindcss.com/docs/font-size)

fun InlineTailwindScope.textXs() {
  property("font-size", "0.75rem")
  property("line-height", "1rem")
}

fun InlineTailwindScope.textSm() {
  property("font-size", "0.875rem")
  property("line-height", "1.25rem")
}

fun InlineTailwindScope.textBase() {
  property("font-size", "1rem")
  property("line-height", "1.5rem")
}

fun InlineTailwindScope.textLg() {
  property("font-size", "1.125rem")
  property("line-height", "1.75rem")
}

fun InlineTailwindScope.textXl() {
  property("font-size", "1.25rem")
  property("line-height", "1.75rem")
}

fun InlineTailwindScope.text2Xl() {
  property("font-size", "1.5rem")
  property("line-height", "2rem")
}

fun InlineTailwindScope.text3Xl() {
  property("font-size", "1.875rem")
  property("line-height", "2.25rem")
}

fun InlineTailwindScope.text4Xl() {
  property("font-size", "2.25rem")
  property("line-height", "2.5rem")
}

fun InlineTailwindScope.text5Xl() {
  property("font-size", "3rem")
  property("line-height", "1")
}

fun InlineTailwindScope.text6Xl() {
  property("font-size", "3.75rem")
  property("line-height", "1")
}

fun InlineTailwindScope.text7Xl() {
  property("font-size", "4.5rem")
  property("line-height", "1")
}

fun InlineTailwindScope.text8Xl() {
  property("font-size", "6rem")
  property("line-height", "1")
}

fun InlineTailwindScope.text9Xl() {
  property("font-size", "8rem")
  property("line-height", "1")
}

fun InlineTailwindScope.textSizePxToRem(n: Float) {
  property("font-size", "${n * 0.0625}rem")
}
