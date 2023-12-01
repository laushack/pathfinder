package io.github.lauzhack.frontend.ui.tailwind

// https://tailwindcss.com/docs/aspect-ratio

fun InlineTailwindScope.aspectAuto() {
  property("aspect-ratio", "auto")
}

fun InlineTailwindScope.aspectSquare() {
  property("aspect-ratio", "square")
}

fun InlineTailwindScope.aspectVideo() {
  property("aspect-ratio", "16/9")
}
