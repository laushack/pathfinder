package io.github.lauzhack.frontend.jokes

import androidx.compose.runtime.Composable
import io.github.lauzhack.frontend.ui.Tokens.body1
import io.github.lauzhack.frontend.ui.Tokens.h6
import io.github.lauzhack.frontend.ui.Tokens.subtitle1
import io.github.lauzhack.frontend.ui.Tokens.white
import io.github.lauzhack.frontend.ui.material.OutlinedButton
import io.github.lauzhack.frontend.ui.tailwind.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement

@Composable
fun JokeScreen(
    attrs: AttrBuilderContext<HTMLDivElement> = {},
    state: JokeScreenState,
) {
  val cardClassName = tailwind {
    flex()
    flexCol()
    itemsStart()
    shadow()
    bgColor(white)
    roundedLg()
    maxWSm()
    overflowClip()
    spaceY(8f)
  }
  Div(
      attrs = {
        attrs()
        classes(cardClassName)
      },
  ) {
    Img(
        src = "https://i1.sndcdn.com/artworks-000409999251-jsaqfk-t500x500.jpg",
        attrs = {
          inlineTailwind {
            aspectSquare()
            wFull()
          }
        },
    )
    H1(
        attrs = {
          inlineTailwind {
            h6()
            mx(16f)
          }
        },
    ) {
      Text("Your favorite joke")
    }
    H2(
        attrs = {
          inlineTailwind {
            subtitle1()
            mx(16f)
          }
        },
    ) {
      Text("#${state.jokeId}")
    }
    Span(
        attrs = {
          inlineTailwind {
            body1()
            mx(16f)
          }
        },
    ) {
      Text(state.jokeText)
    }
    OutlinedButton(
        attrs = {
          onClick { state.onRefreshClick() }
          inlineTailwind {
            mx(8f)
            mb(8f)
          }
        },
    ) {
      Text("Refresh")
    }
  }
}
