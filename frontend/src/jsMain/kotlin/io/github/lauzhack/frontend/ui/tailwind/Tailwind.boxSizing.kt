package io.github.lauzhack.frontend.ui.tailwind

// https://tailwindcss.com/docs/box-sizing

fun InlineTailwindScope.boxBorder() {
  property("box-sizing", "border-box")
}

fun InlineTailwindScope.boxContent() {
  property("box-sizing", "content-box")
}
