package io.github.lauzhack.frontend.ui.tailwind

// FILL (https://tailwindcss.com/docs/fill)

fun InlineTailwindScope.fillNone() {
  property("fill", "none")
}

fun InlineTailwindScope.fillInherit() {
  property("fill", "inherit")
}

fun InlineTailwindScope.fillCurrent() {
  property("fill", "currentColor")
}

fun InlineTailwindScope.fillTransparent() {
  property("fill", "transparent")
}

fun InlineTailwindScope.fillColor(color: String) {
  property("fill", color)
}
