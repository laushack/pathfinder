package io.github.lauzhack.frontend

import androidx.compose.runtime.*
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLBodyElement

/** The root of the application. */
@Composable
fun DOMScope<HTMLBodyElement>.Root() {
  ProvideTailwindStyles {
    Div(
        attrs = {
          inlineTailwind {
            flex()
            flexCol()
            hScreen()
            itemsCenter()
            justifyCenter()
          }
        },
    ) {
      ChatScreen(rememberChatState())
    }
  }
}
