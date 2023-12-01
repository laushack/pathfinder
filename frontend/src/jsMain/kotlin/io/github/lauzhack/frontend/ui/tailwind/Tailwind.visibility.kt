package io.github.lauzhack.frontend.ui.tailwind

// VISIBILITY (https://tailwindcss.com/docs/visibility)

fun InlineTailwindScope.visible() {
  property("visibility", "visible")
}

fun InlineTailwindScope.invisible() {
  property("visibility", "hidden")
}

fun InlineTailwindScope.collapse() {
  property("visibility", "collapse")
}
