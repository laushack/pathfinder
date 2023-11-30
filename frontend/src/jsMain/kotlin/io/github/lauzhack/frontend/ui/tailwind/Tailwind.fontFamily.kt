package io.github.lauzhack.frontend.ui.tailwind

// FONT FAMILY (https://tailwindcss.com/docs/font-family)

fun TailwindScope.fontSans() {
  property(
      "font-family",
      "ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, \"Noto Sans\", sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\", \"Noto Color Emoji\"")
}

fun TailwindScope.fontSerif() {
  property("font-family", "ui-serif, Georgia, Cambria, \"Times New Roman\", Times, serif")
}

fun TailwindScope.fontMono() {
  property(
      "font-family",
      "ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, \"Liberation Mono\", \"Courier New\", monospace")
}
