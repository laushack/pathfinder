package io.github.lauzhack.frontend.ui.tailwind

// GAP (https://tailwindcss.com/docs/gap)

fun InlineTailwindScope.gap0() {
  property("gap", "0")
}

fun InlineTailwindScope.gap(n: Float) {
  property("gap", "${n}px")
}

fun InlineTailwindScope.gapX0() {
  property("column-gap", "0")
}

fun InlineTailwindScope.gapX(n: Float) {
  property("column-gap", "${n}px")
}

fun InlineTailwindScope.gapY0() {
  property("row-gap", "0")
}

fun InlineTailwindScope.gapY(n: Float) {
  property("row-gap", "${n}px")
}
