package io.github.lauzhack.frontend.ui.tailwind

// FLEX GROW (https://tailwindcss.com/docs/flex-grow)

fun TailwindScope.grow() {
  property("flex-grow", "1")
}

fun TailwindScope.grow0() {
  property("flex-grow", "0")
}
