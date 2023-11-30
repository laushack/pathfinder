package io.github.lauzhack.frontend.ui.tailwind

// ALIGN SELF (https://tailwindcss.com/docs/align-self)

fun TailwindScope.selfAuto() {
  property("align-self", "auto")
}

fun TailwindScope.selfStart() {
  property("align-self", "flex-start")
}

fun TailwindScope.selfEnd() {
  property("align-self", "flex-end")
}

fun TailwindScope.selfCenter() {
  property("align-self", "center")
}

fun TailwindScope.selfStretch() {
  property("align-self", "stretch")
}

fun TailwindScope.selfBaseline() {
  property("align-self", "baseline")
}
