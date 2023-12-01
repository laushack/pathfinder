package io.github.lauzhack.frontend.ui.tailwind

// HEIGHT (https://tailwindcss.com/docs/height)

fun InlineTailwindScope.h(n: Float) {
  property("height", "${n}px")
}

fun InlineTailwindScope.hFull() {
  property("height", "100%")
}

fun InlineTailwindScope.hScreen() {
  property("height", "100vh")
}

fun InlineTailwindScope.hMin() {
  property("height", "min-content")
}

fun InlineTailwindScope.hMax() {
  property("height", "max-content")
}

fun InlineTailwindScope.hFit() {
  property("height", "fit-content")
}
