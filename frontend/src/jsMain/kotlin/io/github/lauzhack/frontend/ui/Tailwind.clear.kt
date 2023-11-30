package io.github.lauzhack.frontend.ui

// https://tailwindcss.com/docs/clear

fun TailwindScope.clearLeft() {
  property("clear", "left")
}

fun TailwindScope.clearRight() {
  property("clear", "right")
}

fun TailwindScope.clearBoth() {
  property("clear", "both")
}

fun TailwindScope.clearNone() {
  property("clear", "none")
}
