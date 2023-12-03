package io.github.lauzhack.frontend.features.options

import androidx.compose.runtime.*
import io.github.lauzhack.common.api.PlanningOptions
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

  override var startTimeInput: String by mutableStateOf("")

  override val locationFrom: String?
    get() = backend.planningOptions.startLocation

  override var locationFromInput: String by mutableStateOf("")

  override val locationTo: String?
    get() = backend.planningOptions.endLocation

  override var locationToInput: String by mutableStateOf("")

  override val subscription: String?
    get() = backend.planningOptions.subscription

  override var subscriptionInput: String by mutableStateOf("")

  override fun onToggleModeClick() {
    mode =
        when (mode) {
          OptionsState.Mode.Closed -> {
            locationFromInput = locationFrom ?: ""
            locationToInput = locationTo ?: ""
            subscriptionInput = subscription ?: ""
            startTimeInput = startTime ?: ""
            OptionsState.Mode.Open
          }
          OptionsState.Mode.Open -> OptionsState.Mode.Closed
        }
  }

  override fun onSave() {
    backend.sendPlanningOptions(
        PlanningOptions(
            startLocation = locationFromInput.takeIf { it.isNotBlank() },
            endLocation = locationToInput.takeIf { it.isNotBlank() },
            subscription = subscriptionInput.takeIf { it.isNotBlank() },
            startTime = startTimeInput.takeIf { it.isNotBlank() },
        ),
    )
    mode = OptionsState.Mode.Closed
  }
}
