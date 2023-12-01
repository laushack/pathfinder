package io.github.lauzhack.frontend.ui.tailwind

// PADDING (https://tailwindcss.com/docs/padding)

fun InlineTailwindScope.p(n: Float) {
  property("padding", "${n}px")
}

fun InlineTailwindScope.px(n: Float) {
  property("padding-left", "${n}px")
  property("padding-right", "${n}px")
}

fun InlineTailwindScope.py(n: Float) {
  property("padding-top", "${n}px")
  property("padding-bottom", "${n}px")
}

fun InlineTailwindScope.ps(n: Float) {
  property("padding-inline-start", "${n}px")
}

fun InlineTailwindScope.pe(n: Float) {
  property("padding-inline-end", "${n}px")
}

fun InlineTailwindScope.pt(n: Float) {
  property("padding-top", "${n}px")
}

fun InlineTailwindScope.pr(n: Float) {
  property("padding-right", "${n}px")
}

fun InlineTailwindScope.pb(n: Float) {
  property("padding-bottom", "${n}px")
}

fun InlineTailwindScope.pl(n: Float) {
  property("padding-left", "${n}px")
}
