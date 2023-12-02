package io.github.lauzhack.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryParameters(
    @SerialName("start-location") val startLocation: String? = null,
    @SerialName("end-location") val endLocation: String? = null,
    @SerialName("start-time") val startTime: String? = null,
    @SerialName("start-date") val date: String? = null,
    @SerialName("end-time") val endTime: String? = null,
    @SerialName("subscription") val subscription: String? = null,
)