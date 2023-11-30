package io.github.lauzhack.frontend.ui.tailwind

// DROP SHADOW (https://tailwindcss.com/docs/drop-shadow)

fun TailwindScope.dropShadowSm() {
  property("filter", "drop-shadow(0 1px 1px rgb(0 0 0 / 0.05))")
}

fun TailwindScope.dropShadow() {
  property(
      "filter", "drop-shadow(0 1px 2px rgb(0 0 0 / 0.1)) drop-shadow(0 1px 1px rgb(0 0 0 / 0.06))")
}

fun TailwindScope.dropShadowMd() {
  property(
      "filter", "drop-shadow(0 4px 3px rgb(0 0 0 / 0.07)) drop-shadow(0 2px 2px rgb(0 0 0 / 0.06))")
}

fun TailwindScope.dropShadowLg() {
  property(
      "filter", "drop-shadow(0 10px 8px rgb(0 0 0 / 0.04)) drop-shadow(0 4px 3px rgb(0 0 0 / 0.1))")
}

fun TailwindScope.dropShadowXl() {
  property(
      "filter",
      "drop-shadow(0 20px 13px rgb(0 0 0 / 0.03)) drop-shadow(0 8px 5px rgb(0 0 0 / 0.08))")
}

fun TailwindScope.dropShadow2Xl() {
  property("filter", "drop-shadow(0 25px 25px rgb(0 0 0 / 0.15))")
}

fun TailwindScope.dropShadowNone() {
  property("filter", "drop-shadow(0 0 #0000)")
}
