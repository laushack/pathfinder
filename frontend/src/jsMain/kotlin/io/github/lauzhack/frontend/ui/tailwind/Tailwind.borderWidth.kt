package io.github.lauzhack.frontend.ui.tailwind

// BORDER WIDTH (https://tailwindcss.com/docs/border-width)

fun InlineTailwindScope.border(n: Int) {
  property("border-width", "${n}px")
}

fun InlineTailwindScope.borderPx() {
  property("border-width", "1px")
}
