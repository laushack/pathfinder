package io.github.lauzhack.frontend.ui.tailwind

// BORDER COLOR (https://tailwindcss.com/docs/border-color)

fun TailwindScope.borderInherit() {
  property("border-color", "inherit")
}

fun TailwindScope.borderCurrent() {
  property("border-color", "currentColor")
}

fun TailwindScope.borderTransparent() {
  property("border-color", "transparent")
}

fun TailwindScope.borderColor(color: String) {
  property("border-color", color)
}
