package io.github.lauzhack.frontend.ui.tailwind

// JUSTIFY ITEMS (https://tailwindcss.com/docs/justify-items)

fun TailwindScope.justifyItemsStart() {
  property("justify-items", "start")
}

fun TailwindScope.justifyItemsEnd() {
  property("justify-items", "end")
}

fun TailwindScope.justifyItemsCenter() {
  property("justify-items", "center")
}

fun TailwindScope.justifyItemsStretch() {
  property("justify-items", "stretch")
}
