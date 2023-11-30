package io.github.lauzhack.frontend.ui.tailwind

// POSITION (https://tailwindcss.com/docs/position)

fun TailwindScope.static() {
  property("position", "static")
}

fun TailwindScope.fixed() {
  property("position", "fixed")
}

fun TailwindScope.absolute() {
  property("position", "absolute")
}

fun TailwindScope.relative() {
  property("position", "relative")
}

fun TailwindScope.sticky() {
  property("position", "sticky")
}
