package io.github.lauzhack.frontend.ui.tailwind

// https://tailwindcss.com/docs/clear

fun InlineTailwindScope.clearLeft() {
  property("clear", "left")
}

fun InlineTailwindScope.clearRight() {
  property("clear", "right")
}

fun InlineTailwindScope.clearBoth() {
  property("clear", "both")
}

fun InlineTailwindScope.clearNone() {
  property("clear", "none")
}
