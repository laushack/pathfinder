package io.github.lauzhack.frontend.ui.tailwind

// FLEX WRAP (https://tailwindcss.com/docs/flex-wrap)

fun InlineTailwindScope.flexWrap() {
  property("flex-wrap", "wrap")
}

fun InlineTailwindScope.flexWrapReverse() {
  property("flex-wrap", "wrap-reverse")
}

fun InlineTailwindScope.flexNoWrap() {
  property("flex-wrap", "nowrap")
}
