package io.github.lauzhack.frontend.ui.tailwind

// TEXT TRANSFORM (https://tailwindcss.com/docs/text-transform)

fun InlineTailwindScope.uppercase() {
  property("text-transform", "uppercase")
}

fun InlineTailwindScope.lowercase() {
  property("text-transform", "lowercase")
}

fun InlineTailwindScope.capitalize() {
  property("text-transform", "capitalize")
}

fun InlineTailwindScope.normalCase() {
  property("text-transform", "none")
}
