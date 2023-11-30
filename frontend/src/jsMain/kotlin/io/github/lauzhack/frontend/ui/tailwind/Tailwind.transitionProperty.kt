package io.github.lauzhack.frontend.ui.tailwind

// TRANSITION PROPERTY (https://tailwindcss.com/docs/transition-property)

fun TailwindScope.transitionNone() {
  property("transition-property", "none")
}

fun TailwindScope.transitionAll() {
  property("transition-property", "all")
  property("transition-timing-function", "cubic-bezier(0.4, 0, 0.2, 1)")
  property("transition-duration", "150ms")
}

fun TailwindScope.transitionColors() {
  property(
      "transition-property",
      "color, background-color, border-color, text-decoration-color, fill, stroke")
  property("transition-timing-function", "cubic-bezier(0.4, 0, 0.2, 1)")
  property("transition-duration", "150ms")
}

fun TailwindScope.transitionShadow() {
  property("transition-property", "box-shadow")
  property("transition-timing-function", "cubic-bezier(0.4, 0, 0.2, 1)")
  property("transition-duration", "150ms")
}
