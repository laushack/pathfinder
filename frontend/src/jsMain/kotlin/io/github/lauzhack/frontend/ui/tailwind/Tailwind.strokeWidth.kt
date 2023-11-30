package io.github.lauzhack.frontend.ui.tailwind

// STROKE WIDTH (https://tailwindcss.com/docs/stroke-width)

fun TailwindScope.strokeWidth(width: Float) {
  property("stroke-width", "${width}px")
}
