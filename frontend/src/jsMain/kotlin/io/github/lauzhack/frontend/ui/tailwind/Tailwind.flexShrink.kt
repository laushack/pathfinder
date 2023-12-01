package io.github.lauzhack.frontend.ui.tailwind

// FLEX SHRINK (https://tailwindcss.com/docs/flex-shrink)

fun InlineTailwindScope.shrink() {
  property("flex-shrink", "1")
}

fun InlineTailwindScope.shrink0() {
  property("flex-shrink", "0")
}
