package io.github.lauzhack.frontend.ui.tailwind

// TEXT COLOR (https://tailwindcss.com/docs/text-color)

fun InlineTailwindScope.textColor(color: String) {
  property("color", color)
}

fun InlineTailwindScope.textInherit() {
  property("color", "inherit")
}

fun InlineTailwindScope.textCurrent() {
  property("color", "currentColor")
}

fun InlineTailwindScope.textTransparent() {
  property("color", "transparent")
}
