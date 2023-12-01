package io.github.lauzhack.frontend.ui.tailwind

// https://tailwindcss.com/docs/isolation

fun InlineTailwindScope.isolate() {
  property("isolation", "isolate")
}

fun InlineTailwindScope.isolationAuto() {
  property("isolation", "auto")
}
