package io.github.lauzhack.frontend.ui.tailwind

// OBJECT FIT (https://tailwindcss.com/docs/object-fit)

fun InlineTailwindScope.objectContain() {
  property("object-fit", "contain")
}

fun InlineTailwindScope.objectCover() {
  property("object-fit", "cover")
}

fun InlineTailwindScope.objectFill() {
  property("object-fit", "fill")
}

fun InlineTailwindScope.objectNone() {
  property("object-fit", "none")
}

fun InlineTailwindScope.objectScaleDown() {
  property("object-fit", "scale-down")
}
