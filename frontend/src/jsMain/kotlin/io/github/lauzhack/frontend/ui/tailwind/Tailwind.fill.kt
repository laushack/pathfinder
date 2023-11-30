package io.github.lauzhack.frontend.ui.tailwind

// FILL (https://tailwindcss.com/docs/fill)

fun TailwindScope.fillNone() {
  property("fill", "none")
}

fun TailwindScope.fillInherit() {
  property("fill", "inherit")
}

fun TailwindScope.fillCurrent() {
  property("fill", "currentColor")
}

fun TailwindScope.fillTransparent() {
  property("fill", "transparent")
}

fun TailwindScope.fillColor(color: String) {
  property("fill", color)
}
