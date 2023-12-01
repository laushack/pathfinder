package io.github.lauzhack.frontend.ui.tailwind

// TOP / RIGHT / BOTTOM / LEFT (https://tailwindcss.com/docs/top-right-bottom-left)

fun InlineTailwindScope.inset(n: Float) {
  property("inset", "${n}px")
}

fun InlineTailwindScope.insetX(n: Float) {
  property("left", "${n}px")
  property("right", "${n}px")
}

fun InlineTailwindScope.insetY(n: Float) {
  property("top", "${n}px")
  property("bottom", "${n}px")
}

fun InlineTailwindScope.start(n: Float) {
  property("inset-inline-start", "${n}px")
}

fun InlineTailwindScope.end(n: Float) {
  property("inset-inline-end", "${n}px")
}

fun InlineTailwindScope.top(n: Float) {
  property("top", "${n}px")
}

fun InlineTailwindScope.right(n: Float) {
  property("right", "${n}px")
}

fun InlineTailwindScope.bottom(n: Float) {
  property("bottom", "${n}px")
}

fun InlineTailwindScope.left(n: Float) {
  property("left", "${n}px")
}
