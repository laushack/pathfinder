package io.github.lauzhack.frontend.ui.tailwind

// OUTLINE WIDTH (https://tailwindcss.com/docs/outline-width)

fun InlineTailwindScope.outline(n: Float) {
  property("outline-width", "${n}px")
}
