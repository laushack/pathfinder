package io.github.lauzhack.frontend.ui.tailwind

// FONT SMOOTHING (https://tailwindcss.com/docs/font-smoothing)

fun InlineTailwindScope.antialiased() {
  property("-webkit-font-smoothing", "antialiased")
  property("-moz-osx-font-smoothing", "grayscale")
}

fun InlineTailwindScope.subpixelAntialiased() {
  property("-webkit-font-smoothing", "auto")
  property("-moz-osx-font-smoothing", "auto")
}
