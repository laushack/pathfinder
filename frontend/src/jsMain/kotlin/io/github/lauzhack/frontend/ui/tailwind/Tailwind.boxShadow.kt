package io.github.lauzhack.frontend.ui.tailwind

fun InlineTailwindScope.shadowSm() {
  property("box-shadow", "0 1px 2px 0 rgb(0 0 0 / 0.05)")
}

fun InlineTailwindScope.shadow() {
  property("box-shadow", "0 1px 3px 0 rgb(0 0 0 / 0.1), 0 1px 2px -1px rgb(0 0 0 / 0.1)")
}

fun InlineTailwindScope.shadowMd() {
  property("box-shadow", "0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1)")
}

fun InlineTailwindScope.shadowLg() {
  property("box-shadow", "0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1)")
}

fun InlineTailwindScope.shadowXl() {
  property("box-shadow", "0 20px 25px -5px rgb(0 0 0 / 0.1), 0 8px 10px -6px rgb(0 0 0 / 0.1)")
}

fun InlineTailwindScope.shadow2Xl() {
  property("box-shadow", "0 25px 50px -12px rgb(0 0 0 / 0.25)")
}

fun InlineTailwindScope.shadowInner() {
  property("box-shadow", "inset 0 2px 4px 0 rgb(0 0 0 / 0.05)")
}

fun InlineTailwindScope.shadowNone() {
  property("box-shadow", "0 0 #0000")
}
