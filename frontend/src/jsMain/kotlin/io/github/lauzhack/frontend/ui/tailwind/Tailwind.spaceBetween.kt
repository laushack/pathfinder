package io.github.lauzhack.frontend.ui.tailwind

// SPACE BETWEEN (https://tailwindcss.com/docs/space)

private fun TailwindScope.applyToChildren(a: String, b: String) {
  // TODO("implement this.")
}

fun TailwindScope.spaceX(n: Float) {
  // Assuming a function `applyToChildren` exists that applies the given property to children
  // elements
  applyToChildren("margin-left", "${n}px")
}

fun TailwindScope.spaceY(n: Float) {
  // Assuming a function `applyToChildren` exists that applies the given property to children
  // elements
  applyToChildren("margin-top", "${n}px")
}
