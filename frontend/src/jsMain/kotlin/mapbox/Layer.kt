package mapbox

external interface Layer {
  var id: String
  var type: String
  var source: Source
  var layout: Layout
  var paint: Paint
}

fun Layer(block: Layer.() -> Unit): Layer {
  val layer = js("{}").unsafeCast<Layer>()
  block(layer)
  return layer
}

external interface Layout {
  var lineCap: String
  var lineJoin: String
}

fun Layout(block: Layout.() -> Unit): Layout {
  val layout = js("{}").unsafeCast<Layout>()
  block(layout)
  return layout
}

external interface Paint {
  var lineColor: String
  var lineWidth: Double
}

fun Paint(block: Paint.() -> Unit): Paint {
  val paint = js("{}").unsafeCast<Paint>()
  block(paint)
  return paint
}

external interface Source {
  var type: String
  var data: String
}

fun Source(block: Source.() -> Unit): Source {
  val source = js("{}").unsafeCast<Source>()
  block(source)
  return source
}
