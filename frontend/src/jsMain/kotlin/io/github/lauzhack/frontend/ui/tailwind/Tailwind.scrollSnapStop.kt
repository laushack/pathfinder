package io.github.lauzhack.frontend.ui.tailwind

// SCROLL SNAP STOP (https://tailwindcss.com/docs/scroll-snap-stop)

fun TailwindScope.snapNormal() {
  property("scroll-snap-stop", "normal")
}

fun TailwindScope.snapAlways() {
  property("scroll-snap-stop", "always")
}
