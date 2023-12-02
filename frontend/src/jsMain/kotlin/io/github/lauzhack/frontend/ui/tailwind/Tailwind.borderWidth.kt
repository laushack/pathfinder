package io.github.lauzhack.frontend.ui.tailwind

// BORDER WIDTH (https://tailwindcss.com/docs/border-width)

fun InlineTailwindScope.border(n: Float) {
  property("border-width", "${n}px")
}

fun InlineTailwindScope.borderPx() {
  property("border-width", "1px")
}

fun InlineTailwindScope.borderDashed() {
  property("border-style", "dashed")
}

fun InlineTailwindScope.borderDotted() {
  property("border-style", "dotted")
}
