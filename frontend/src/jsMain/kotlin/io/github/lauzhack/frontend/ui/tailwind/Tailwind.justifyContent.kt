package io.github.lauzhack.frontend.ui.tailwind

// JUSTIFY CONTENT (https://tailwindcss.com/docs/justify-content)

fun TailwindScope.justifyNormal() {
  property("justify-content", "normal")
}

fun TailwindScope.justifyStart() {
  property("justify-content", "flex-start")
}

fun TailwindScope.justifyEnd() {
  property("justify-content", "flex-end")
}

fun TailwindScope.justifyCenter() {
  property("justify-content", "center")
}

fun TailwindScope.justifyBetween() {
  property("justify-content", "space-between")
}

fun TailwindScope.justifyAround() {
  property("justify-content", "space-around")
}

fun TailwindScope.justifyEvenly() {
  property("justify-content", "space-evenly")
}

fun TailwindScope.justifyStretch() {
  property("justify-content", "stretch")
}
