package io.github.lauzhack.backend.api.openStreetMap

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenStreetMapResponse(
    @SerialName("place_id") val placeId: Long,
    @SerialName("licence") val licence: String,
    @SerialName("osm_type") val osmType: String,
    @SerialName("osm_id") val osmId: Long,
    @SerialName("lat") val lat: String,
    @SerialName("lon") val lon: String,
    @SerialName("class") val classX: String,
    @SerialName("type") val type: String,
    @SerialName("place_rank") val placeRank: Long,
    @SerialName("importance") val importance: Double,
    @SerialName("addresstype") val addressType: String,
    @SerialName("name") val name: String,
    @SerialName("display_name") val displayName: String,
    @SerialName("boundingbox") val boundingBox: List<String>,

)
