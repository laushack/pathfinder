package io.github.lauzhack.frontend.ui.tailwind

// MIN-WIDTH (https://tailwindcss.com/docs/min-width)

fun TailwindScope.minW(n: Float) {
  property("min-width", "${n}px")
}
