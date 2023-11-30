package io.github.lauzhack.frontend.ui.tailwind

// USER SELECT (https://tailwindcss.com/docs/user-select)

fun TailwindScope.selectNone() {
  property("user-select", "none")
}

fun TailwindScope.selectText() {
  property("user-select", "text")
}

fun TailwindScope.selectAll() {
  property("user-select", "all")
}

fun TailwindScope.selectAuto() {
  property("user-select", "auto")
}
