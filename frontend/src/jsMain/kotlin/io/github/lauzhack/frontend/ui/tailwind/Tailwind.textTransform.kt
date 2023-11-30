package io.github.lauzhack.frontend.ui.tailwind

// TEXT TRANSFORM (https://tailwindcss.com/docs/text-transform)

fun TailwindScope.uppercase() {
  property("text-transform", "uppercase")
}

fun TailwindScope.lowercase() {
  property("text-transform", "lowercase")
}

fun TailwindScope.capitalize() {
  property("text-transform", "capitalize")
}

fun TailwindScope.normalCase() {
  property("text-transform", "none")
}
