package io.github.lauzhack.frontend.ui.tailwind

// ALIGN ITEMS (https://tailwindcss.com/docs/align-items)

fun TailwindScope.itemsStart() {
  property("align-items", "flex-start")
}

fun TailwindScope.itemsEnd() {
  property("align-items", "flex-end")
}

fun TailwindScope.itemsCenter() {
  property("align-items", "center")
}

fun TailwindScope.itemsBaseline() {
  property("align-items", "baseline")
}

fun TailwindScope.itemsStretch() {
  property("align-items", "stretch")
}
