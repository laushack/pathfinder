package io.github.lauzhack.frontend.ui.tailwind

// ALIGN ITEMS (https://tailwindcss.com/docs/align-items)

fun InlineTailwindScope.itemsStart() {
  property("align-items", "flex-start")
}

fun InlineTailwindScope.itemsEnd() {
  property("align-items", "flex-end")
}

fun InlineTailwindScope.itemsCenter() {
  property("align-items", "center")
}

fun InlineTailwindScope.itemsBaseline() {
  property("align-items", "baseline")
}

fun InlineTailwindScope.itemsStretch() {
  property("align-items", "stretch")
}
