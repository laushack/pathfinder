package io.github.lauzhack.frontend.features.assistant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import io.github.lauzhack.frontend.ui.Tokens.body1
import io.github.lauzhack.frontend.ui.Tokens.cffPinkDark
import io.github.lauzhack.frontend.ui.Tokens.cffPinkLightVeryLight
import io.github.lauzhack.frontend.ui.Tokens.cffPinkVeryLight
import io.github.lauzhack.frontend.ui.Tokens.white
import io.github.lauzhack.frontend.ui.material.*
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement

/** Returns an element which displays the chat with the assistant. */
@Composable
fun ChatFloatingWindow(chat: ChatState) {
  Div(
      attrs = {
        inlineTailwind {
          bgColor(white)
          shadow()
          roundedXl()
          maxWLg()
          flex()
          flexCol()
          overflowClip()
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
    }
    Div(attrs = { classes(className) }) {
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
            bgColor(cffPinkLightVeryLight)
          } else {
            ms(48f)
            bgColor(cffPinkVeryLight)
            textColor(cffPinkDark)
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
