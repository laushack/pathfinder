package io.github.lauzhack.frontend.ui.tailwind

// ALIGN SELF (https://tailwindcss.com/docs/align-self)

fun InlineTailwindScope.selfAuto() {
  property("align-self", "auto")
}

fun InlineTailwindScope.selfStart() {
  property("align-self", "flex-start")
}

fun InlineTailwindScope.selfEnd() {
  property("align-self", "flex-end")
}

fun InlineTailwindScope.selfCenter() {
  property("align-self", "center")
}

fun InlineTailwindScope.selfStretch() {
  property("align-self", "stretch")
}

fun InlineTailwindScope.selfBaseline() {
  property("align-self", "baseline")
}
