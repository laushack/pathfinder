package mapbox.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import mapbox.MapOptions
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

/** The token used to access the MapBox API. */
val MapBoxToken =
    "pk.eyJ1IjoiYWxleGFuZHJlcGl2ZXRlYXVlcGZsIiwiYSI6ImNscG9xbzI1YzB1bjIyaXA2cTFmcjU5cWIifQ.xeUfLjWhZJfdNlsbowshyQ"

@Composable
fun MapBox(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
) {
  Div(
      attrs = {
        id("map")
        attrs?.invoke(this)
      },
  ) {
    DisposableEffect(Unit) {
      val map =
          mapbox.Map(
              MapOptions {
                accessToken = MapBoxToken // set access token
                container = "map" // container ID
                style = "mapbox://styles/mapbox/streets-v12" // style URL
                center = arrayOf(6.5593, 46.5188) // starting position [lng, lat]
                zoom = 9.0 // starting zoom
              },
          )
      onDispose { map.remove() }
    }
  }
}
