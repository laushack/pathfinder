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
  }
  val imgClassName = tailwind {
    aspectSquare()
    wFull()
    mb(8f)
  }
  val titleClassName = tailwind {
    h6()
    mx(16f)
    mb(8f)
  }
  val subtitleClassName = tailwind {
    subtitle1()
    mx(16f)
    mb(8f)
  }
  val bodyClassName = tailwind {
    body1()
    mx(16f)
    mb(8f)
  }
  val buttonClassName = tailwind {
    mx(8f)
    mb(8f)
  }
  Div(
      attrs = {
        attrs()
        classes(cardClassName)
      },
  ) {
    Img(
        src = "https://i1.sndcdn.com/artworks-000409999251-jsaqfk-t500x500.jpg",
        attrs = { classes(imgClassName) },
    )
    H1(attrs = { classes(titleClassName) }) { Text("Your favorite joke") }
    H2(attrs = { classes(subtitleClassName) }) { Text("#${state.jokeId}") }
    Span(attrs = { classes(bodyClassName) }) { Text(state.jokeText) }
    OutlinedButton(
        attrs = {
          onClick { state.onRefreshClick() }
          classes(buttonClassName)
        },
    ) {
      Text("Refresh")
    }
  }
}
