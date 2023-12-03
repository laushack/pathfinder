package mapbox

external interface MarkerOptions {
  var color: String
}

fun MarkerOptions(block: MarkerOptions.() -> Unit): MarkerOptions {
  val options = js("{}").unsafeCast<MarkerOptions>()
  block(options)
  return options
}
