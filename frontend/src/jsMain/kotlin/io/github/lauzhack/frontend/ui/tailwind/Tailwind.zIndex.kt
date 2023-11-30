package io.github.lauzhack.frontend.ui.tailwind

// Z-INDEX (https://tailwindcss.com/docs/z-index)

fun TailwindScope.z(n: Int) {
  property("z-index", n.toString())
}

fun TailwindScope.zAuto() {
  property("z-index", "auto")
}
