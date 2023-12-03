package io.github.lauzhack.common.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssistantToUserSetPlanning(
    val planningOptions: PlanningOptions,
) : BackendToUserMessage()

@Serializable
data class UserToAssistantSetPlanning(
    val planningOptions: PlanningOptions,
) : UserToBackendMessage()

@Serializable
data class PlanningOptions(
    @SerialName("start-location") val startLocation: String? = null,
    @SerialName("end-location") val endLocation: String? = null,
    @SerialName("start-time") val startTime: String? = null,
    @SerialName("subscription") val subscription: String? = null,
)

/** Takes the non-null values from [other] and uses them to update this [PlanningOptions]. */
fun PlanningOptions.updatedWith(other: PlanningOptions): PlanningOptions =
    PlanningOptions(
        startLocation = other.startLocation ?: startLocation,
        endLocation = other.endLocation ?: endLocation,
        startTime = other.startTime ?: startTime,
        subscription = other.subscription ?: subscription,
    )

fun PlanningOptions.invalidateStartLocation(): PlanningOptions = copy(startLocation = null)

fun PlanningOptions.invalidateEndLocation(): PlanningOptions = copy(endLocation = null)

fun PlanningOptions.isSufficient(): Boolean =
    listOf(startLocation, endLocation, startTime).all { it != null }

fun PlanningOptions.isComplete(): Boolean =
    listOf(startLocation, endLocation, startTime, subscription).all { it != null }
