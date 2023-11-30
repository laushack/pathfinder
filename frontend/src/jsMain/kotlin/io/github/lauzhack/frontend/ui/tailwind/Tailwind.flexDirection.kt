package io.github.lauzhack.frontend.ui.tailwind

// FLEX DIRECTION (https://tailwindcss.com/docs/flex-direction)

fun TailwindScope.flexRow() {
  property("flex-direction", "row")
}

fun TailwindScope.flexRowReverse() {
  property("flex-direction", "row-reverse")
}

fun TailwindScope.flexCol() {
  property("flex-direction", "column")
}

fun TailwindScope.flexColReverse() {
  property("flex-direction", "column-reverse")
}
