package io.github.lauzhack.frontend.ui.tailwind

// Z-INDEX (https://tailwindcss.com/docs/z-index)

fun InlineTailwindScope.z(n: Int) {
  property("z-index", n.toString())
}

fun InlineTailwindScope.zAuto() {
  property("z-index", "auto")
}
