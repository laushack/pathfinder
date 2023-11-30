package io.github.lauzhack.frontend.ui.tailwind

// OBJECT FIT (https://tailwindcss.com/docs/object-fit)

fun TailwindScope.objectContain() {
  property("object-fit", "contain")
}

fun TailwindScope.objectCover() {
  property("object-fit", "cover")
}

fun TailwindScope.objectFill() {
  property("object-fit", "fill")
}

fun TailwindScope.objectNone() {
  property("object-fit", "none")
}

fun TailwindScope.objectScaleDown() {
  property("object-fit", "scale-down")
}
