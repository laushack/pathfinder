package io.github.lauzhack.frontend.ui.tailwind

// TRANSLATE (https://tailwindcss.com/docs/translate)

fun InlineTailwindScope.translateX(n: Float) {
  property("transform", "translateX(${n}px)")
}

fun InlineTailwindScope.translateY(n: Float) {
  property("transform", "translateY(${n}px)")
}

fun InlineTailwindScope.translateXY(x: Float, y: Float) {
  property("transform", "translate(${x}px, ${y}px)")
}
