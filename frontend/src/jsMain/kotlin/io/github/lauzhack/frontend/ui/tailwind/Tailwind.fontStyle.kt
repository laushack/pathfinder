package io.github.lauzhack.frontend.ui.tailwind

// FONT STYLE (https://tailwindcss.com/docs/font-style)

fun TailwindScope.italic() {
  property("font-style", "italic")
}

fun TailwindScope.notItalic() {
  property("font-style", "normal")
}
