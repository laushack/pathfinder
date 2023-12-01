package io.github.lauzhack.frontend.ui.tailwind

// OUTLINE COLOR (https://tailwindcss.com/docs/outline-color)

fun InlineTailwindScope.outlineInherit() {
  property("outline-color", "inherit")
}

fun InlineTailwindScope.outlineCurrent() {
  property("outline-color", "currentColor")
}

fun InlineTailwindScope.outlineTransparent() {
  property("outline-color", "transparent")
}

fun InlineTailwindScope.outlineColor(color: String) {
  property("outline-color", color)
}
