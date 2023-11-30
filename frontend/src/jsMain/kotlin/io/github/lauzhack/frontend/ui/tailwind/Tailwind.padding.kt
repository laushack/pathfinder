package io.github.lauzhack.frontend.ui.tailwind

// PADDING (https://tailwindcss.com/docs/padding)

fun TailwindScope.p(n: Float) {
  property("padding", "${n}px")
}

fun TailwindScope.px(n: Float) {
  property("padding-left", "${n}px")
  property("padding-right", "${n}px")
}

fun TailwindScope.py(n: Float) {
  property("padding-top", "${n}px")
  property("padding-bottom", "${n}px")
}

fun TailwindScope.ps(n: Float) {
  property("padding-inline-start", "${n}px")
}

fun TailwindScope.pe(n: Float) {
  property("padding-inline-end", "${n}px")
}

fun TailwindScope.pt(n: Float) {
  property("padding-top", "${n}px")
}

fun TailwindScope.pr(n: Float) {
  property("padding-right", "${n}px")
}

fun TailwindScope.pb(n: Float) {
  property("padding-bottom", "${n}px")
}

fun TailwindScope.pl(n: Float) {
  property("padding-left", "${n}px")
}
