package io.github.lauzhack.frontend.ui.tailwind

// STROKE WIDTH (https://tailwindcss.com/docs/stroke-width)

fun InlineTailwindScope.strokeWidth(width: Float) {
  property("stroke-width", "${width}px")
}
