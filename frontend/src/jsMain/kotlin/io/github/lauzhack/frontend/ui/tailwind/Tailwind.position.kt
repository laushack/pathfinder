package io.github.lauzhack.frontend.ui.tailwind

// POSITION (https://tailwindcss.com/docs/position)

fun InlineTailwindScope.static() {
  property("position", "static")
}

fun InlineTailwindScope.fixed() {
  property("position", "fixed")
}

fun InlineTailwindScope.absolute() {
  property("position", "absolute")
}

fun InlineTailwindScope.relative() {
  property("position", "relative")
}

fun InlineTailwindScope.sticky() {
  property("position", "sticky")
}
