package io.github.lauzhack.frontend.ui.tailwind

fun TailwindScope.active(
    scope: TailwindScope.() -> Unit,
) = pseudoClass("active", scope)

fun TailwindScope.hover(
    scope: TailwindScope.() -> Unit,
) = pseudoClass("hover", scope)

fun TailwindScope.children(
    scope: TailwindScope.() -> Unit,
) = selector(">*+*", scope)
