package io.github.lauzhack.frontend.ui.tailwind

// JUSTIFY ITEMS (https://tailwindcss.com/docs/justify-items)

fun InlineTailwindScope.justifyItemsStart() {
  property("justify-items", "start")
}

fun InlineTailwindScope.justifyItemsEnd() {
  property("justify-items", "end")
}

fun InlineTailwindScope.justifyItemsCenter() {
  property("justify-items", "center")
}

fun InlineTailwindScope.justifyItemsStretch() {
  property("justify-items", "stretch")
}
