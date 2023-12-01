package io.github.lauzhack.frontend.ui.tailwind

// FLEX DIRECTION (https://tailwindcss.com/docs/flex-direction)

fun InlineTailwindScope.flexRow() {
  property("flex-direction", "row")
}

fun InlineTailwindScope.flexRowReverse() {
  property("flex-direction", "row-reverse")
}

fun InlineTailwindScope.flexCol() {
  property("flex-direction", "column")
}

fun InlineTailwindScope.flexColReverse() {
  property("flex-direction", "column-reverse")
}
