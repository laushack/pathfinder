@file:JsNonModule
@file:JsModule("mapbox-gl")

package mapbox

/** Mapbox access token. */
external var accessToken: String

/** A Mapbox map. */
external class Map(options: MapOptions = definedExternally) {
  fun addLayer(layer: Layer)
  fun remove()
}
