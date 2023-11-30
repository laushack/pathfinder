package io.github.lauzhack.frontend.ui.tailwind

// OUTLINE COLOR (https://tailwindcss.com/docs/outline-color)

fun TailwindScope.outlineInherit() {
  property("outline-color", "inherit")
}

fun TailwindScope.outlineCurrent() {
  property("outline-color", "currentColor")
}

fun TailwindScope.outlineTransparent() {
  property("outline-color", "transparent")
}

fun TailwindScope.outlineColor(color: String) {
  property("outline-color", color)
}
