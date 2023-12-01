package io.github.lauzhack.frontend.ui.tailwind

// BORDER RADIUS (https://tailwindcss.com/docs/border-radius)

fun InlineTailwindScope.roundedNone() {
  property("border-radius", "0px")
}

fun InlineTailwindScope.roundedSm() {
  property("border-radius", "0.125rem")
}

fun InlineTailwindScope.rounded() {
  property("border-radius", "0.25rem")
}

fun InlineTailwindScope.roundedMd() {
  property("border-radius", "0.375rem")
}

fun InlineTailwindScope.roundedLg() {
  property("border-radius", "0.5rem")
}

fun InlineTailwindScope.roundedXl() {
  property("border-radius", "0.75rem")
}

fun InlineTailwindScope.rounded2Xl() {
  property("border-radius", "1rem")
}

fun InlineTailwindScope.rounded3Xl() {
  property("border-radius", "1.5rem")
}

fun InlineTailwindScope.roundedFull() {
  property("border-radius", "9999px")
}
