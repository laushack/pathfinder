package io.github.lauzhack.frontend.ui.tailwind

// FONT SIZE (https://tailwindcss.com/docs/font-size)

fun TailwindScope.textXs() {
  property("font-size", "0.75rem")
  property("line-height", "1rem")
}

fun TailwindScope.textSm() {
  property("font-size", "0.875rem")
  property("line-height", "1.25rem")
}

fun TailwindScope.textBase() {
  property("font-size", "1rem")
  property("line-height", "1.5rem")
}

fun TailwindScope.textLg() {
  property("font-size", "1.125rem")
  property("line-height", "1.75rem")
}

fun TailwindScope.textXl() {
  property("font-size", "1.25rem")
  property("line-height", "1.75rem")
}

fun TailwindScope.text2Xl() {
  property("font-size", "1.5rem")
  property("line-height", "2rem")
}

fun TailwindScope.text3Xl() {
  property("font-size", "1.875rem")
  property("line-height", "2.25rem")
}

fun TailwindScope.text4Xl() {
  property("font-size", "2.25rem")
  property("line-height", "2.5rem")
}

fun TailwindScope.text5Xl() {
  property("font-size", "3rem")
  property("line-height", "1")
}

fun TailwindScope.text6Xl() {
  property("font-size", "3.75rem")
  property("line-height", "1")
}

fun TailwindScope.text7Xl() {
  property("font-size", "4.5rem")
  property("line-height", "1")
}

fun TailwindScope.text8Xl() {
  property("font-size", "6rem")
  property("line-height", "1")
}

fun TailwindScope.text9Xl() {
  property("font-size", "8rem")
  property("line-height", "1")
}

fun TailwindScope.textSizePxToRem(n: Float) {
  property("font-size", "${n * 0.0625}rem")
}
