package io.github.lauzhack.frontend.ui.tailwind

// SCROLL SNAP TYPE (https://tailwindcss.com/docs/scroll-snap-type)

fun InlineTailwindScope.snapNone() {
  property("scroll-snap-type", "none")
}

fun InlineTailwindScope.snapX() {
  property("scroll-snap-type", "x mandatory")
}

fun InlineTailwindScope.snapY() {
  property("scroll-snap-type", "y mandatory")
}

fun InlineTailwindScope.snapBoth() {
  property("scroll-snap-type", "both mandatory")
}
