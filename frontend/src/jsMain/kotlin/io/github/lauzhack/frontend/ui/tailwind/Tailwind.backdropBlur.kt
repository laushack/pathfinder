package io.github.lauzhack.frontend.ui.tailwind

// BACKDROP BLUR (https://tailwindcss.com/docs/backdrop-blur)

fun TailwindScope.backdropBlurNone() {
  property("backdrop-filter", "blur(0)")
}

fun TailwindScope.backdropBlurSm() {
  property("backdrop-filter", "blur(4px)")
}

fun TailwindScope.backdropBlur() {
  property("backdrop-filter", "blur(8px)")
}

fun TailwindScope.backdropBlurMd() {
  property("backdrop-filter", "blur(12px)")
}

fun TailwindScope.backdropBlurLg() {
  property("backdrop-filter", "blur(16px)")
}

fun TailwindScope.backdropBlurXl() {
  property("backdrop-filter", "blur(24px)")
}

fun TailwindScope.backdropBlur2Xl() {
  property("backdrop-filter", "blur(40px)")
}

fun TailwindScope.backdropBlur3Xl() {
  property("backdrop-filter", "blur(64px)")
}
