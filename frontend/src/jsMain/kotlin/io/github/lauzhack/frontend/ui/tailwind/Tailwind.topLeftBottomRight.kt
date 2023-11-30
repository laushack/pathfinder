package io.github.lauzhack.frontend.ui.tailwind

// TOP / RIGHT / BOTTOM / LEFT (https://tailwindcss.com/docs/top-right-bottom-left)

fun TailwindScope.inset(n: Float) {
  property("inset", "${n}px")
}

fun TailwindScope.insetX(n: Float) {
  property("left", "${n}px")
  property("right", "${n}px")
}

fun TailwindScope.insetY(n: Float) {
  property("top", "${n}px")
  property("bottom", "${n}px")
}

fun TailwindScope.start(n: Float) {
  property("inset-inline-start", "${n}px")
}

fun TailwindScope.end(n: Float) {
  property("inset-inline-end", "${n}px")
}

fun TailwindScope.top(n: Float) {
  property("top", "${n}px")
}

fun TailwindScope.right(n: Float) {
  property("right", "${n}px")
}

fun TailwindScope.bottom(n: Float) {
  property("bottom", "${n}px")
}

fun TailwindScope.left(n: Float) {
  property("left", "${n}px")
}
