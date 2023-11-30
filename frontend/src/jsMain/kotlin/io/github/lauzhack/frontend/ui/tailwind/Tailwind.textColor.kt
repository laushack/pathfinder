package io.github.lauzhack.frontend.ui.tailwind

// TEXT COLOR (https://tailwindcss.com/docs/text-color)

fun TailwindScope.textColor(color: String) {
  property("color", color)
}

fun TailwindScope.textInherit() {
  property("color", "inherit")
}

fun TailwindScope.textCurrent() {
  property("color", "currentColor")
}

fun TailwindScope.textTransparent() {
  property("color", "transparent")
}
