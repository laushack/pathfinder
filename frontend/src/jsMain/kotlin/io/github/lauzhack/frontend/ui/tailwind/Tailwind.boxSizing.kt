package io.github.lauzhack.frontend.ui.tailwind

// https://tailwindcss.com/docs/box-sizing

fun TailwindScope.boxBorder() {
  property("box-sizing", "border-box")
}

fun TailwindScope.boxContent() {
  property("box-sizing", "content-box")
}
