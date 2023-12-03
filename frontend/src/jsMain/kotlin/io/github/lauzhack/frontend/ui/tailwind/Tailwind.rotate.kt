package io.github.lauzhack.frontend.ui.tailwind

fun InlineTailwindScope.rotate(n: Float) {
  property("transform", "rotate(${n}deg)")
}
