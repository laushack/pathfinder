package io.github.lauzhack.frontend.ui.tailwind

// TRANSITION PROPERTY (https://tailwindcss.com/docs/transition-property)

fun InlineTailwindScope.transitionNone() {
  property("transition-property", "none")
}

fun InlineTailwindScope.transitionAll(duration: Int = 150) {
  property("transition-property", "all")
  property("transition-timing-function", "cubic-bezier(0.4, 0, 0.2, 1)")
  property("transition-duration", "${duration}ms")
}

fun InlineTailwindScope.transitionColors() {
  property(
      "transition-property",
      "color, background-color, border-color, text-decoration-color, fill, stroke")
  property("transition-timing-function", "cubic-bezier(0.4, 0, 0.2, 1)")
  property("transition-duration", "150ms")
}

fun InlineTailwindScope.transitionShadow() {
  property("transition-property", "box-shadow")
  property("transition-timing-function", "cubic-bezier(0.4, 0, 0.2, 1)")
  property("transition-duration", "150ms")
}
