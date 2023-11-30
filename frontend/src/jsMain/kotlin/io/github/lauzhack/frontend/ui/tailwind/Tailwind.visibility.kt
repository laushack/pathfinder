package io.github.lauzhack.frontend.ui.tailwind

// VISIBILITY (https://tailwindcss.com/docs/visibility)

fun TailwindScope.visible() {
  property("visibility", "visible")
}

fun TailwindScope.invisible() {
  property("visibility", "hidden")
}

fun TailwindScope.collapse() {
  property("visibility", "collapse")
}
