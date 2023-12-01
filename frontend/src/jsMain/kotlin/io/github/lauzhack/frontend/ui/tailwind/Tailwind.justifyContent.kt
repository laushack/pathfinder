package io.github.lauzhack.frontend.ui.tailwind

// JUSTIFY CONTENT (https://tailwindcss.com/docs/justify-content)

fun InlineTailwindScope.justifyNormal() {
  property("justify-content", "normal")
}

fun InlineTailwindScope.justifyStart() {
  property("justify-content", "flex-start")
}

fun InlineTailwindScope.justifyEnd() {
  property("justify-content", "flex-end")
}

fun InlineTailwindScope.justifyCenter() {
  property("justify-content", "center")
}

fun InlineTailwindScope.justifyBetween() {
  property("justify-content", "space-between")
}

fun InlineTailwindScope.justifyAround() {
  property("justify-content", "space-around")
}

fun InlineTailwindScope.justifyEvenly() {
  property("justify-content", "space-evenly")
}

fun InlineTailwindScope.justifyStretch() {
  property("justify-content", "stretch")
}
