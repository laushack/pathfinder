package io.github.lauzhack.frontend.ui.tailwind

// BACKGROUND COLOR (https://tailwindcss.com/docs/background-color)

fun InlineTailwindScope.bgColor(color: String) {
  property("background-color", color)
}

fun InlineTailwindScope.bgInherit() {
  property("background-color", "inherit")
}

fun InlineTailwindScope.bgCurrent() {
  property("background-color", "currentColor")
}

fun InlineTailwindScope.bgTransparent() {
  property("background-color", "transparent")
}
