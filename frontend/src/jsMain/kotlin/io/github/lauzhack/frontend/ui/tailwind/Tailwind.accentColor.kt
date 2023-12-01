package io.github.lauzhack.frontend.ui.tailwind

// ACCENT COLOR (https://tailwindcss.com/docs/accent-color)

fun InlineTailwindScope.accentInherit() {
  property("accent-color", "inherit")
}

fun InlineTailwindScope.accentCurrent() {
  property("accent-color", "currentColor")
}

fun InlineTailwindScope.accentTransparent() {
  property("accent-color", "transparent")
}

fun InlineTailwindScope.accentColor(color: String) {
  property("accent-color", color)
}
