package io.github.lauzhack.frontend.features.assistant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import io.github.lauzhack.frontend.ui.Tokens.body1
import io.github.lauzhack.frontend.ui.Tokens.cffPinkLight
import io.github.lauzhack.frontend.ui.Tokens.cffRed
import io.github.lauzhack.frontend.ui.Tokens.cffRedLight
import io.github.lauzhack.frontend.ui.Tokens.cffRedVeryLight
import io.github.lauzhack.frontend.ui.Tokens.h5
import io.github.lauzhack.frontend.ui.Tokens.subtitle1
import io.github.lauzhack.frontend.ui.Tokens.white
import io.github.lauzhack.frontend.ui.material.*
import io.github.lauzhack.frontend.ui.material.Button
import io.github.lauzhack.frontend.ui.material.Input
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement

/** Returns an element which displays the chat with the assistant. */
@Composable
fun ChatFloatingWindow(chat: ChatState) {
  Div(
      attrs = {
        inlineTailwind {
          bgColor(white)
          roundedXl()
          flex()
          flexCol()
          overflowClip()
          shadow()
        }
      },
  ) {
    val className = tailwind {
      flex()
      flexCol()
      p(16f)
      spaceY(16f)
      shadowInner()
      bgColor(white)
      overflowYScroll()
      grow0()
    }
    Div(attrs = { classes(className) }) {
      key(Unit) { ChatPlaceholder() }
      chat.conversation.forEach { message ->
        key(message) {
          Div {
            ChatMessage(
                role = message.role,
                text = message.content,
            )
          }
        }
      }
    }
    Div(
        attrs = {
          inlineTailwind {
            h(1f)
            wFull()
            bgColor(cffPinkLight)
          }
        },
    )
    val suggestions = chat.conversation.lastOrNull()?.suggestions ?: emptyList()
    ChatInput(
        input = chat.input,
        onInputChange = { chat.input = it },
        onSendClick = chat::onSendClick,
        suggestions = suggestions,
        onSuggestionClick = chat::onSuggestionClick,
        attrs = { inlineTailwind { selfStretch() } },
    )
  }
}

@Composable
private fun ChatPlaceholder() {
  Div(
      attrs = {
        inlineTailwind {
          flex()
          flexCol()
          itemsCenter()
          gap(8f)
        }
      },
  ) {
    Img(
        src = "./assets/illustration.png",
        attrs = { inlineTailwind { wFull() } },
    )
    Div(
        attrs = {
          inlineTailwind {
            flex()
            flexRow()
            gap(8f)
            itemsCenter()
          }
        },
    ) {
      Img(
          src = "./assets/cff.png",
          attrs = { inlineTailwind { w(72f) } },
      )
      Span(
          attrs = { inlineTailwind { h5() } },
      ) {
        Text("Plan your P+R trip!")
      }
      Img(
          src = "./assets/cff.png",
          attrs = { inlineTailwind { w(72f) } },
      )
    }
    Span(
        attrs = {
          inlineTailwind {
            subtitle1()
            flex()
            flexCol()
            gap(8f)
          }
        },
    ) {
      Span {
        Text(
            "Ask me anything about P+R trips, I'll do my best to help you! Let me know where you want to go, where you're coming from, and I'll suggest you the best P+R trip.")
      }
      Span { Text("Start chatting below:") }
    }
  }
}

/**
 * Displays a text message in the chat.
 *
 * @param role The role of the user who sent the message.
 * @param text The content of the message.
 */
@Composable
private fun ChatMessage(
    role: ChatState.Role,
    text: String,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
) {
  Div(
      attrs = {
        inlineTailwind {
          body1()
          p(16f)
          roundedXl()
          if (role == ChatState.Role.User) {
            me(48f)
            border(2f)
            borderColor(cffRedLight)
            borderDashed()
          } else {
            ms(48f)
            bgColor(cffRedVeryLight)
            border(2f)
            borderColor(cffRed)
            // borderDotted()
          }
        }
        attrs?.invoke(this)
      },
  ) {
    Text(text)
  }
}

/** Displays an input to send a message to the assistant. */
@Composable
private fun ChatInput(
    input: String,
    onInputChange: (String) -> Unit,
    onSendClick: () -> Unit,
    suggestions: List<ChatState.Suggestion>,
    onSuggestionClick: (ChatState.Suggestion) -> Unit,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
) {

  Div(
      attrs = {
        inlineTailwind {
          flex()
          flexCol()
          bgColor(white)
          shadowLg()
        }
        attrs?.invoke(this)
      },
  ) {
    val suggestionsClassName = tailwind {
      flex()
      flexRow()
      flexWrap()
      itemsCenter()
      gap(8f)
      p(8f)
    }
    Div(attrs = { classes(suggestionsClassName) }) {
      suggestions.forEach { suggestion ->
        key(suggestion) {
          ChatSuggestion(
              suggestion = suggestion,
              onClick = { onSuggestionClick(suggestion) },
          )
        }
      }
    }
    Div(
        attrs = {
          inlineTailwind {
            flex()
            flexRow()
            itemsCenter()
            p(8f)
            gap(8f)
          }
        },
    ) {
      Input(
          type = InputType.Text,
          attrs = {
            placeholder("Where do you want to go, and how?")
            inlineTailwind { grow() }
            value(input)
            onInput { onInputChange(it.value) }
            onKeyUp { event ->
              if (event.code == "Enter") {
                onSendClick()
              }
            }
          },
      )
      IconButton(
          attrs = {
            onClick { onSendClick() }
            inlineTailwind {
              h(48f)
              w(48f)
            }
          },
      ) {
        Icon(Icons.SendIcon)
      }
    }
  }
}

@Composable
private fun ChatSuggestion(
    suggestion: ChatState.Suggestion,
    onClick: () -> Unit,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
) {
  Button(
      attrs = {
        inlineTailwind { roundedFull() }
        onClick { onClick() }
        attrs?.invoke(this)
      },
  ) {
    Text(suggestion.text)
  }
}
