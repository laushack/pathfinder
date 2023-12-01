package io.github.lauzhack.frontend.ui.tailwind

// SPACE BETWEEN (https://tailwindcss.com/docs/space)

fun TailwindScope.spaceX(n: Float) {
  children { property("margin-left", "${n}px") }
}

fun TailwindScope.spaceY(n: Float) {
  children { property("margin-top", "${n}px") }
}
