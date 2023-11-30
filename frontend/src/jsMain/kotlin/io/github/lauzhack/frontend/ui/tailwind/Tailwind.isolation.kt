package io.github.lauzhack.frontend.ui.tailwind

// https://tailwindcss.com/docs/isolation

fun TailwindScope.isolate() {
  property("isolation", "isolate")
}

fun TailwindScope.isolationAuto() {
  property("isolation", "auto")
}
