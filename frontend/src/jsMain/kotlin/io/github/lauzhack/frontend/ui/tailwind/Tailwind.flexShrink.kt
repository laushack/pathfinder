package io.github.lauzhack.frontend.ui.tailwind

// FLEX SHRINK (https://tailwindcss.com/docs/flex-shrink)

fun TailwindScope.shrink() {
  property("flex-shrink", "1")
}

fun TailwindScope.shrink0() {
  property("flex-shrink", "0")
}
