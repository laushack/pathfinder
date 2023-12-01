package io.github.lauzhack.frontend.ui.tailwind

// SCROLL SNAP ALIGN (https://tailwindcss.com/docs/scroll-snap-align)

fun InlineTailwindScope.snapStart() {
  property("scroll-snap-align", "start")
}

fun InlineTailwindScope.snapEnd() {
  property("scroll-snap-align", "end")
}

fun InlineTailwindScope.snapCenter() {
  property("scroll-snap-align", "center")
}

fun InlineTailwindScope.snapAlignNone() {
  property("scroll-snap-align", "none")
}
