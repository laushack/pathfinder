package io.github.lauzhack.frontend

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.block
import io.github.lauzhack.frontend.ui.flex
import io.github.lauzhack.frontend.ui.tailwind
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLBodyElement

private fun hello(): String = "123"

/** The root of the application. */
@Composable
fun DOMScope<HTMLBodyElement>.Root() {
  Div(
      attrs = {
        tailwind {
          flex()
          // flexCol()
        }
      }) {
        H1 { Text("Welcome to Lauzhack 2023") }
        Text("This is a test")
        Button(attrs = { tailwind { block() } }) { Text(hello()) }
      }
}
