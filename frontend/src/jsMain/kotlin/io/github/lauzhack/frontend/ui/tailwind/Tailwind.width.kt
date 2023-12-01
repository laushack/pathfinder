package io.github.lauzhack.frontend.ui.tailwind

// WIDTH (https://tailwindcss.com/docs/width)

fun InlineTailwindScope.w(n: Float) {
  property("width", "${n}px")
}

fun InlineTailwindScope.wFull() {
  property("width", "100%")
}
