package io.github.lauzhack.frontend.ui.tailwind

// OVERSCROLL BEHAVIOR (https://tailwindcss.com/docs/overscroll-behavior)

fun TailwindScope.overscrollAuto() {
    property("overscroll-behavior", "auto")
}

fun TailwindScope.overscrollContain() {
    property("overscroll-behavior", "contain")
}

fun TailwindScope.overscrollNone() {
    property("overscroll-behavior", "none")
}

fun TailwindScope.overscrollYAuto() {
    property("overscroll-behavior-y", "auto")
}

fun TailwindScope.overscrollYContain() {
    property("overscroll-behavior-y", "contain")
}

fun TailwindScope.overscrollYNone() {
    property("overscroll-behavior-y", "none")
}

fun TailwindScope.overscrollXAuto() {
    property("overscroll-behavior-x", "auto")
}

fun TailwindScope.overscrollXContain() {
    property("overscroll-behavior-x", "contain")
}

fun TailwindScope.overscrollXNone() {
    property("overscroll-behavior-x", "none")
}
