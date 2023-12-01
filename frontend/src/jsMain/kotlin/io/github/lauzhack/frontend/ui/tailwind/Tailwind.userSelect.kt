package io.github.lauzhack.frontend.ui.tailwind

// USER SELECT (https://tailwindcss.com/docs/user-select)

fun InlineTailwindScope.selectNone() {
  property("user-select", "none")
}

fun InlineTailwindScope.selectText() {
  property("user-select", "text")
}

fun InlineTailwindScope.selectAll() {
  property("user-select", "all")
}

fun InlineTailwindScope.selectAuto() {
  property("user-select", "auto")
}
