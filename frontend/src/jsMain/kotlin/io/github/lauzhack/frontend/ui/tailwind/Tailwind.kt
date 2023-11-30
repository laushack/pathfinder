package io.github.lauzhack.frontend.ui.tailwind

import org.jetbrains.compose.web.attributes.AttrsScope
import org.w3c.dom.Element

/** A scope for defining CSS properties. */
fun interface TailwindScope {

  /**
   * Defines a CSS property.
   *
   * @param name The name of the property.
   * @param value The value of the property.
   */
  fun property(name: String, value: String)
}

/**
 * Defines some inline CSS properties that should be applied to the element.
 *
 * @param T The type of the element.
 * @param scope The scope in which to define the CSS properties.
 * @receiver The context in which the CSS properties are applied.
 */
fun <T : Element> AttrsScope<T>.tailwind(scope: TailwindScope.() -> Unit) {
  style { TailwindScope { name, value -> property(name, value) }.scope() }
}
