package io.github.lauzhack.frontend.ui.tailwind

// BORDER COLOR (https://tailwindcss.com/docs/border-color)

fun InlineTailwindScope.borderInherit() {
  property("border-color", "inherit")
}

fun InlineTailwindScope.borderCurrent() {
  property("border-color", "currentColor")
}

fun InlineTailwindScope.borderTransparent() {
  property("border-color", "transparent")
}

fun InlineTailwindScope.borderColor(color: String) {
  property("border-color", color)
}
