package io.github.lauzhack.frontend.ui.tailwind

// MARGIN (https://tailwindcss.com/docs/margin)

fun InlineTailwindScope.m(n: Float) {
  property("margin", "${n}px")
}

fun InlineTailwindScope.mx(n: Float) {
  property("margin-left", "${n}px")
  property("margin-right", "${n}px")
}

fun InlineTailwindScope.my(n: Float) {
  property("margin-top", "${n}px")
  property("margin-bottom", "${n}px")
}

fun InlineTailwindScope.ms(n: Float) {
  property("margin-inline-start", "${n}px")
}

fun InlineTailwindScope.me(n: Float) {
  property("margin-inline-end", "${n}px")
}

fun InlineTailwindScope.mt(n: Float) {
  property("margin-top", "${n}px")
}

fun InlineTailwindScope.mr(n: Float) {
  property("margin-right", "${n}px")
}

fun InlineTailwindScope.mb(n: Float) {
  property("margin-bottom", "${n}px")
}

fun InlineTailwindScope.ml(n: Float) {
  property("margin-left", "${n}px")
}

fun InlineTailwindScope.mAuto() {
  property("margin", "auto")
}

fun InlineTailwindScope.mxAuto() {
  property("margin-left", "auto")
  property("margin-right", "auto")
}

fun InlineTailwindScope.myAuto() {
  property("margin-top", "auto")
  property("margin-bottom", "auto")
}

fun InlineTailwindScope.msAuto() {
  property("margin-inline-start", "auto")
}

fun InlineTailwindScope.meAuto() {
  property("margin-inline-end", "auto")
}

fun InlineTailwindScope.mtAuto() {
  property("margin-top", "auto")
}

fun InlineTailwindScope.mrAuto() {
  property("margin-right", "auto")
}

fun InlineTailwindScope.mbAuto() {
  property("margin-bottom", "auto")
}

fun InlineTailwindScope.mlAuto() {
  property("margin-left", "auto")
}
