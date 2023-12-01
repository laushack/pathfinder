package io.github.lauzhack.frontend.ui.tailwind

// FONT STYLE (https://tailwindcss.com/docs/font-style)

fun InlineTailwindScope.italic() {
  property("font-style", "italic")
}

fun InlineTailwindScope.notItalic() {
  property("font-style", "normal")
}
