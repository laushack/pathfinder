package io.github.lauzhack.frontend.ui.tailwind

// OVERSCROLL BEHAVIOR (https://tailwindcss.com/docs/overscroll-behavior)

fun InlineTailwindScope.overscrollAuto() {
  property("overscroll-behavior", "auto")
}

fun InlineTailwindScope.overscrollContain() {
  property("overscroll-behavior", "contain")
}

fun InlineTailwindScope.overscrollNone() {
  property("overscroll-behavior", "none")
}

fun InlineTailwindScope.overscrollYAuto() {
  property("overscroll-behavior-y", "auto")
}

fun InlineTailwindScope.overscrollYContain() {
  property("overscroll-behavior-y", "contain")
}

fun InlineTailwindScope.overscrollYNone() {
  property("overscroll-behavior-y", "none")
}

fun InlineTailwindScope.overscrollXAuto() {
  property("overscroll-behavior-x", "auto")
}

fun InlineTailwindScope.overscrollXContain() {
  property("overscroll-behavior-x", "contain")
}

fun InlineTailwindScope.overscrollXNone() {
  property("overscroll-behavior-x", "none")
}
