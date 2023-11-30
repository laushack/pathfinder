package io.github.lauzhack.frontend.ui.tailwind

// ACCENT COLOR (https://tailwindcss.com/docs/accent-color)

fun TailwindScope.accentInherit() {
  property("accent-color", "inherit")
}

fun TailwindScope.accentCurrent() {
  property("accent-color", "currentColor")
}

fun TailwindScope.accentTransparent() {
  property("accent-color", "transparent")
}

fun TailwindScope.accentColor(color: String) {
  property("accent-color", color)
}
