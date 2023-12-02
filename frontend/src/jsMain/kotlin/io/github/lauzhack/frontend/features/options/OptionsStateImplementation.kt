package io.github.lauzhack.frontend.features.options

import androidx.compose.runtime.*
import io.github.lauzhack.frontend.api.backend.BackendService
import io.github.lauzhack.frontend.api.backend.LocalBackendService

@Composable
fun rememberOptionsState(): OptionsState {
  val backend = LocalBackendService.current
  return remember(backend) { OptionsStateImplementation(backend) }
}

private class OptionsStateImplementation(private val backend: BackendService) : OptionsState {

  override var mode: OptionsState.Mode by mutableStateOf(OptionsState.Mode.Closed)
    private set

  override val startTime: String?
    get() = backend.planningOptions.startTime

  override val locationFrom: String?
    get() = backend.planningOptions.startLocation

  override val locationTo: String?
    get() = backend.planningOptions.endLocation

  override val subscription: String?
    get() = backend.planningOptions.subscription

  override fun onToggleModeClick() {
    mode =
        when (mode) {
          OptionsState.Mode.Closed -> OptionsState.Mode.Open
          OptionsState.Mode.Open -> OptionsState.Mode.Closed
        }
  }
}
