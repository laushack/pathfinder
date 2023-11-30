package io.github.lauzhack.frontend.ui.tailwind

// MARGIN (https://tailwindcss.com/docs/margin)

fun TailwindScope.m(n: Float) {
  property("margin", "${n}px")
}

fun TailwindScope.mx(n: Float) {
  property("margin-left", "${n}px")
  property("margin-right", "${n}px")
}

fun TailwindScope.my(n: Float) {
  property("margin-top", "${n}px")
  property("margin-bottom", "${n}px")
}

fun TailwindScope.ms(n: Float) {
  property("margin-inline-start", "${n}px")
}

fun TailwindScope.me(n: Float) {
  property("margin-inline-end", "${n}px")
}

fun TailwindScope.mt(n: Float) {
  property("margin-top", "${n}px")
}

fun TailwindScope.mr(n: Float) {
  property("margin-right", "${n}px")
}

fun TailwindScope.mb(n: Float) {
  property("margin-bottom", "${n}px")
}

fun TailwindScope.ml(n: Float) {
  property("margin-left", "${n}px")
}

fun TailwindScope.mAuto() {
  property("margin", "auto")
}

fun TailwindScope.mxAuto() {
  property("margin-left", "auto")
  property("margin-right", "auto")
}

fun TailwindScope.myAuto() {
  property("margin-top", "auto")
  property("margin-bottom", "auto")
}

fun TailwindScope.msAuto() {
  property("margin-inline-start", "auto")
}

fun TailwindScope.meAuto() {
  property("margin-inline-end", "auto")
}

fun TailwindScope.mtAuto() {
  property("margin-top", "auto")
}

fun TailwindScope.mrAuto() {
  property("margin-right", "auto")
}

fun TailwindScope.mbAuto() {
  property("margin-bottom", "auto")
}

fun TailwindScope.mlAuto() {
  property("margin-left", "auto")
}
