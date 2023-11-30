package io.github.lauzhack.frontend.ui.tailwind

// HEIGHT (https://tailwindcss.com/docs/height)

fun TailwindScope.h(n: Float) {
  property("height", "${n}px")
}

fun TailwindScope.hFull() {
  property("height", "100%")
}

fun TailwindScope.hScreen() {
  property("height", "100vh")
}

fun TailwindScope.hMin() {
  property("height", "min-content")
}

fun TailwindScope.hMax() {
  property("height", "max-content")
}

fun TailwindScope.hFit() {
  property("height", "fit-content")
}
