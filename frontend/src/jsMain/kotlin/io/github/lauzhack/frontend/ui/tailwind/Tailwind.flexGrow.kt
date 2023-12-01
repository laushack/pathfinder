package io.github.lauzhack.frontend.ui.tailwind

// FLEX GROW (https://tailwindcss.com/docs/flex-grow)

fun InlineTailwindScope.grow() {
  property("flex-grow", "1")
}

fun InlineTailwindScope.grow0() {
  property("flex-grow", "0")
}
