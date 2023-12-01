package io.github.lauzhack.frontend.ui.tailwind

// SCROLL SNAP STOP (https://tailwindcss.com/docs/scroll-snap-stop)

fun InlineTailwindScope.snapNormal() {
  property("scroll-snap-stop", "normal")
}

fun InlineTailwindScope.snapAlways() {
  property("scroll-snap-stop", "always")
}
