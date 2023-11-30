package io.github.lauzhack.frontend.ui.tailwind

// OUTLINE WIDTH (https://tailwindcss.com/docs/outline-width)

fun TailwindScope.outline(n: Float) {
  property("outline-width", "${n}px")
}
