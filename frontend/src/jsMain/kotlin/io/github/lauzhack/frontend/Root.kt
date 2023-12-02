package io.github.lauzhack.frontend

import androidx.compose.runtime.*
import io.github.lauzhack.frontend.api.backend.ProvideBackendService
import io.github.lauzhack.frontend.features.assistant.ChatFloatingWindow
import io.github.lauzhack.frontend.features.assistant.rememberChatState
import io.github.lauzhack.frontend.features.options.Options
import io.github.lauzhack.frontend.features.options.rememberOptionsState
import io.github.lauzhack.frontend.ui.Tokens.cffPinkVeryLight
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLBodyElement

/** The root of the application. */
@Composable
fun DOMScope<HTMLBodyElement>.Root() {
  ProvideTailwindStyles {
    ProvideBackendService {
      Div(
          attrs = {
            inlineTailwind {
              bgColor(cffPinkVeryLight)
              flex()
              flexCol()
              hScreen()
              px(32f)
              py(16f)
            }
          },
      ) {
        ChatFloatingWindow(rememberChatState())
        Options(rememberOptionsState())
      }
    }
  }
}
