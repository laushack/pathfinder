package io.github.lauzhack.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryParameters(
    @SerialName("start-location") val startLocation: String?,
    @SerialName("end-location") val endLocation: String,
    @SerialName("start-time") val startTime: String,
    @SerialName("start-date") val date: String,
    @SerialName("end-time") val endTime: String,
    @SerialName("subscription") val subscription: String,
)