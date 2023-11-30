package io.github.lauzhack.frontend.ui.tailwind

// https://tailwindcss.com/docs/aspect-ratio

fun TailwindScope.aspectAuto() {
  property("aspect-ratio", "auto")
}

fun TailwindScope.aspectSquare() {
  property("aspect-ratio", "square")
}

fun TailwindScope.aspectVideo() {
  property("aspect-ratio", "16/9")
}
