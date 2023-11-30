package io.github.lauzhack.frontend.ui.tailwind

// WIDTH (https://tailwindcss.com/docs/width)

fun TailwindScope.w(n: Float) {
  property("width", "${n}px")
}
