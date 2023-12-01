package io.github.lauzhack.frontend.ui.tailwind

// TRANSLATE (https://tailwindcss.com/docs/translate)

fun InlineTailwindScope.translateX(n: Float) {
  property("transform", "translateX(${n}px)")
}

fun InlineTailwindScope.translateY(n: Float) {
  property("transform", "translateY(${n}px)")
}
