package io.github.lauzhack.frontend.ui.tailwind

// CARET COLOR (https://tailwindcss.com/docs/caret-color)

fun TailwindScope.caretInherit() {
  property("caret-color", "inherit")
}

fun TailwindScope.caretCurrent() {
  property("caret-color", "currentColor")
}

fun TailwindScope.caretTransparent() {
  property("caret-color", "transparent")
}

fun TailwindScope.caretColor(color: String) {
  property("caret-color", color)
}
