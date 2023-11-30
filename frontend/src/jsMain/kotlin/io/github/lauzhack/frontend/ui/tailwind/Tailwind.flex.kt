package io.github.lauzhack.frontend.ui.tailwind

// FLEX (https://tailwindcss.com/docs/flex)

fun TailwindScope.flex1() {
  property("flex", "1 1 0%")
}

fun TailwindScope.flexAuto() {
  property("flex", "1 1 auto")
}

fun TailwindScope.flexInitial() {
  property("flex", "0 1 auto")
}

fun TailwindScope.flexNone() {
  property("flex", "none")
}
