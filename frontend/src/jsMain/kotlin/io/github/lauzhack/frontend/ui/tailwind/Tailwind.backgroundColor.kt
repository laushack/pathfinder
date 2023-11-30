package io.github.lauzhack.frontend.ui.tailwind

// BACKGROUND COLOR (https://tailwindcss.com/docs/background-color)

fun TailwindScope.bgColor(color: String) {
  property("background-color", color)
}

fun TailwindScope.bgInherit() {
  property("background-color", "inherit")
}

fun TailwindScope.bgCurrent() {
  property("background-color", "currentColor")
}

fun TailwindScope.bgTransparent() {
  property("background-color", "transparent")
}
