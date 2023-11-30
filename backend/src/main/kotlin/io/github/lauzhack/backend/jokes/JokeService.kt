package io.github.lauzhack.backend.jokes

import io.github.lauzhack.common.DadJoke
import kotlinx.coroutines.flow.Flow

/**
 * A service which lets all the users access the same joke, and refresh it when needed. If a user
 * refreshes the joke, all the other users will see the new joke.
 */
interface JokeService {

  /** Requests that a new joke be fetched from the API. */
  suspend fun refresh()

  /** Returns the latest dad joke, which is */
  fun latestJoke(): Flow<DadJoke>
}
