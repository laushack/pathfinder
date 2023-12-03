@file:JsNonModule
@file:JsModule("mapbox-gl")

package mapbox

external class Popup(options: PopupOptions = definedExternally) {
    fun setText(text: String): Popup

    fun setHTML(html: String): Popup
}