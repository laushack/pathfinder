package io.github.lauzhack.frontend.ui.tailwind

// BORDER RADIUS (https://tailwindcss.com/docs/border-radius)

fun TailwindScope.roundedNone() {
  property("border-radius", "0px")
}

fun TailwindScope.roundedSm() {
  property("border-radius", "0.125rem")
}

fun TailwindScope.rounded() {
  property("border-radius", "0.25rem")
}

fun TailwindScope.roundedMd() {
  property("border-radius", "0.375rem")
}

fun TailwindScope.roundedLg() {
  property("border-radius", "0.5rem")
}

fun TailwindScope.roundedXl() {
  property("border-radius", "0.75rem")
}

fun TailwindScope.rounded2Xl() {
  property("border-radius", "1rem")
}

fun TailwindScope.rounded3Xl() {
  property("border-radius", "1.5rem")
}

fun TailwindScope.roundedFull() {
  property("border-radius", "9999px")
}
