package io.github.lauzhack.frontend.ui.tailwind

// BORDER WIDTH (https://tailwindcss.com/docs/border-width)

fun TailwindScope.border(n: Int) {
  property("border-width", "${n}px")
}

fun TailwindScope.borderPx() {
  property("border-width", "1px")
}
