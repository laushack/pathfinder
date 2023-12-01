package io.github.lauzhack.frontend.ui.tailwind

// BACKDROP BLUR (https://tailwindcss.com/docs/backdrop-blur)

fun InlineTailwindScope.backdropBlurNone() {
  property("backdrop-filter", "blur(0)")
}

fun InlineTailwindScope.backdropBlurSm() {
  property("backdrop-filter", "blur(4px)")
}

fun InlineTailwindScope.backdropBlur() {
  property("backdrop-filter", "blur(8px)")
}

fun InlineTailwindScope.backdropBlurMd() {
  property("backdrop-filter", "blur(12px)")
}

fun InlineTailwindScope.backdropBlurLg() {
  property("backdrop-filter", "blur(16px)")
}

fun InlineTailwindScope.backdropBlurXl() {
  property("backdrop-filter", "blur(24px)")
}

fun InlineTailwindScope.backdropBlur2Xl() {
  property("backdrop-filter", "blur(40px)")
}

fun InlineTailwindScope.backdropBlur3Xl() {
  property("backdrop-filter", "blur(64px)")
}
