package io.github.lauzhack.frontend.ui.tailwind

// FLEX WRAP (https://tailwindcss.com/docs/flex-wrap)

fun TailwindScope.flexWrap() {
  property("flex-wrap", "wrap")
}

fun TailwindScope.flexWrapReverse() {
  property("flex-wrap", "wrap-reverse")
}

fun TailwindScope.flexNoWrap() {
  property("flex-wrap", "nowrap")
}
