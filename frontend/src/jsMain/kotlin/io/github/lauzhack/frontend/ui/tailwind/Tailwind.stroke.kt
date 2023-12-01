package io.github.lauzhack.frontend.ui.tailwind

// STROKE (https://tailwindcss.com/docs/stroke)

fun InlineTailwindScope.strokeNone() {
  property("stroke", "none")
}

fun InlineTailwindScope.strokeInherit() {
  property("stroke", "inherit")
}

fun InlineTailwindScope.strokeCurrent() {
  property("stroke", "currentColor")
}

fun InlineTailwindScope.strokeTransparent() {
  property("stroke", "transparent")
}

fun InlineTailwindScope.strokeColor(color: String) {
  property("stroke", color)
}
