package mapbox.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import io.github.lauzhack.frontend.api.backend.LocalBackendService
import io.github.lauzhack.frontend.ui.Tokens
import io.github.lauzhack.frontend.ui.Tokens.blue
import io.github.lauzhack.frontend.ui.Tokens.cffRed
import mapbox.*
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
  val trip = LocalBackendService.current.trip
  Div(
      attrs = {
        id("map")
        attrs?.invoke(this)
      },
  ) {
    DisposableEffect(Unit, trip) {
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

      val stopList = trip?.stops ?: emptyList()

      stopList.forEach {
        Marker(
                MarkerOptions {
                  color =
                      when (it.name) {
                        "Start" -> {
                          blue
                        }
                        "End" -> {
                          blue
                        }
                        else -> {
                          cffRed
                        }
                      }
                },
            )
            .apply { setLngLat(arrayOf(it.longitude, it.latitude)) }
            .addTo(map)
      }

      if (stopList.size >= 2) {
        val lineString =
            turf.lineString(
                (trip?.stops ?: emptyList())
                    .map { stop -> arrayOf(stop.longitude, stop.latitude) }
                    .toTypedArray())

        map.asDynamic().on("load") {
          map.addLayer(
              Layer {
                id = "route"
                type = "line"
                source = Source {
                  type = "geojson"
                  data = lineString
                }
                layout = Layout {
                  lineJoin = "round"
                  lineCap = "round"
                }
                paint = Paint {
                  lineColor = Tokens.cffRed
                  lineWidth = 32.0
                }
              },
          )
        }
      }

      onDispose { map.remove() }
    }
  }
}
