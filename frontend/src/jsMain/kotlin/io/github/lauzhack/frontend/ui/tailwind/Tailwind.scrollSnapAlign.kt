package io.github.lauzhack.frontend.ui.tailwind

// SCROLL SNAP ALIGN (https://tailwindcss.com/docs/scroll-snap-align)

fun TailwindScope.snapStart() {
  property("scroll-snap-align", "start")
}

fun TailwindScope.snapEnd() {
  property("scroll-snap-align", "end")
}

fun TailwindScope.snapCenter() {
  property("scroll-snap-align", "center")
}

fun TailwindScope.snapAlignNone() {
  property("scroll-snap-align", "none")
}
