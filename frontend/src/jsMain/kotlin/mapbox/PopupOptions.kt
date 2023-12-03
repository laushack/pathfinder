package mapbox

external interface PopupOptions {
    var offset: Int
    var closeButton: Boolean
    var closeOnClick: Boolean
    var className: String?
}

fun PopupOptions(block: PopupOptions.() -> Unit): PopupOptions {
    val options = js("{}").unsafeCast<PopupOptions>()
    block(options)
    return options
}
