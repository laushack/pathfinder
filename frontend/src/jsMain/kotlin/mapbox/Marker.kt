@file:JsNonModule
@file:JsModule("mapbox-gl")

package mapbox

external class Marker(options: MarkerOptions = definedExternally) {
  fun setLngLat(lngLat: Array<Double>): Marker

  fun addTo(map: Map): Marker
}
