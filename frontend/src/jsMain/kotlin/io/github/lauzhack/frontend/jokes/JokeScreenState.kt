package io.github.lauzhack.frontend.jokes

/** The state of the joke screen. */
interface JokeScreenState {

  /** The text of the joke to be displayed. */
  val jokeText: String

  /** The callback to be invoked when the user clicks the refresh button. */
  fun onRefreshClick()
}