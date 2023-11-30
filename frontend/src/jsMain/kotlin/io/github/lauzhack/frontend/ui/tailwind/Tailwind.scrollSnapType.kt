package io.github.lauzhack.frontend.ui.tailwind

// SCROLL SNAP TYPE (https://tailwindcss.com/docs/scroll-snap-type)

fun TailwindScope.snapNone() {
  property("scroll-snap-type", "none")
}

fun TailwindScope.snapX() {
  property("scroll-snap-type", "x mandatory")
}

fun TailwindScope.snapY() {
  property("scroll-snap-type", "y mandatory")
}

fun TailwindScope.snapBoth() {
  property("scroll-snap-type", "both mandatory")
}
