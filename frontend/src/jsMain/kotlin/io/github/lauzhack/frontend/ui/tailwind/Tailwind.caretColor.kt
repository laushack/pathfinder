package io.github.lauzhack.frontend.ui.tailwind

// CARET COLOR (https://tailwindcss.com/docs/caret-color)

fun InlineTailwindScope.caretInherit() {
  property("caret-color", "inherit")
}

fun InlineTailwindScope.caretCurrent() {
  property("caret-color", "currentColor")
}

fun InlineTailwindScope.caretTransparent() {
  property("caret-color", "transparent")
}

fun InlineTailwindScope.caretColor(color: String) {
  property("caret-color", color)
}
