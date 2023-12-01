package io.github.lauzhack.frontend.ui.tailwind

// FLEX (https://tailwindcss.com/docs/flex)

fun InlineTailwindScope.flex1() {
  property("flex", "1 1 0%")
}

fun InlineTailwindScope.flexAuto() {
  property("flex", "1 1 auto")
}

fun InlineTailwindScope.flexInitial() {
  property("flex", "0 1 auto")
}

fun InlineTailwindScope.flexNone() {
  property("flex", "none")
}
