package mapbox

/** The options to create a new Mapbox map. */
external interface MapOptions {
  var accessToken: String
  var container: String
  var style: String
  var center: Array<Double>
  var zoom: Double
}

/** Creates a new Mapbox map options. */
fun MapOptions(block: MapOptions.() -> Unit): MapOptions {
  val options = js("{}").unsafeCast<MapOptions>()
  block(options)
  return options
}
