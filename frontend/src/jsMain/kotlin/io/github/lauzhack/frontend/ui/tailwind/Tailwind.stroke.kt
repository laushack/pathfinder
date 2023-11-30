package io.github.lauzhack.frontend.ui.tailwind

// STROKE (https://tailwindcss.com/docs/stroke)

fun TailwindScope.strokeNone() {
  property("stroke", "none")
}

fun TailwindScope.strokeInherit() {
  property("stroke", "inherit")
}

fun TailwindScope.strokeCurrent() {
  property("stroke", "currentColor")
}

fun TailwindScope.strokeTransparent() {
  property("stroke", "transparent")
}

fun TailwindScope.strokeColor(color: String) {
  property("stroke", color)
}
