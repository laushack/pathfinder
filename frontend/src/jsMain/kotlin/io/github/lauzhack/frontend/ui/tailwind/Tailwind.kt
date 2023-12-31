package io.github.lauzhack.frontend.ui.tailwind

import androidx.compose.runtime.*
import kotlin.random.Random
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Style
import org.w3c.dom.Element

/** Defines a CompositionLocal for [TailwindStyles]. */
val LocalTailwindStyles = compositionLocalOf<TailwindStyles> { error("Missing CompositionLocal.") }

/** An interface representing the styles in TailwindCSS. */
interface TailwindStyles {

  /** Returns the class name for the set of properties, which are added to the styles. */
  @Composable fun properties(properties: TailwindProperties): String

  /** Defines the styles in TailwindCSS. */
  @Composable fun StyleSheet()
}

/**
 * Provides the [TailwindStyles] to the content.
 *
 * @param content The content to which to provide the [TailwindStyles].
 */
@Composable
fun ProvideTailwindStyles(content: @Composable () -> Unit) {
  val styles = remember { TailwindStylesImpl() }
  styles.StyleSheet()
  CompositionLocalProvider(LocalTailwindStyles provides styles, content = content)
}

/**
 * Returns the class name for the set of properties, which are added to the global Tailwind styles
 * as long as the composition is active.
 *
 * @param scope The scope in which to define the CSS properties.
 */
@Composable
fun tailwind(scope: TailwindScope.() -> Unit): String {
  val styles = LocalTailwindStyles.current
  val properties =
      remember(scope) {
        val properties = mutableSetOf<TailwindProperty>()
        RecordingTailwindScope(properties = properties).scope()
        TailwindProperties(properties)
      }
  return styles.properties(properties)
}

/**
 * Applies the given TailwindCSS styles to the element. Some styles require composition to be
 * active, in which case the [tailwind] function should be used instead. However, this function is
 * more performant and convenient for styles that do not require composition to be active.
 *
 * @param scope The scope in which to define the CSS properties.
 */
fun <T : Element> AttrsScope<T>.inlineTailwind(scope: InlineTailwindScope.() -> Unit) {
  style { InlineTailwindScope { name, value -> property(name, value) }.apply(scope) }
}

/** Create a [Styled] composable which applies the specified TailwindCSS styles to the element. */
@Composable
fun <T : Element> Styled(
    element: @Composable (attrs: AttrBuilderContext<T>?, content: ContentBuilder<T>?) -> Unit,
    attrs: AttrBuilderContext<T>? = null,
    content: ContentBuilder<T>? = null,
    scope: TailwindScope.() -> Unit,
) {
  val className = tailwind(scope)
  element(
      {
        attrs?.invoke(this)
        classes(className)
      },
      content,
  )
}

/** A scope for defining properties in an inline fashion. */
fun interface InlineTailwindScope {

  /**
   * Defines a CSS property.
   *
   * @param name The name of the property.
   * @param value The value of the property.
   */
  fun property(name: String, value: String)
}

/** A scope for defining CSS properties. */
interface TailwindScope : InlineTailwindScope {

  /**
   * Uses the specified selector to define CSS properties.
   *
   * @param name The name of the selector.
   * @param scope The scope for which to define the CSS properties.
   */
  fun selector(name: String, scope: TailwindScope.() -> Unit)

  /**
   * Uses the specified pseudo-class to define CSS properties.
   *
   * @param name The name of the pseudo-class.
   * @param scope The scope for which to define the CSS properties.
   */
  fun pseudoClass(name: String, scope: TailwindScope.() -> Unit) {
    return selector(":$name", scope)
  }
}

/** A single property in TailwindCSS. */
data class TailwindProperty(
    val selectors: List<String>,
    val name: String,
    val value: String,
)

/** A set of properties in TailwindCSS. */
data class TailwindProperties(
    val properties: Set<TailwindProperty>,
)

/** A scope which records CSS properties that are applied to the element. */
private class RecordingTailwindScope(
    private val selectors: List<String> = emptyList(),
    private val properties: MutableSet<TailwindProperty> = mutableSetOf(),
) : TailwindScope {

  override fun property(name: String, value: String) {
    properties.add(TailwindProperty(selectors, name, value))
  }

  override fun selector(name: String, scope: TailwindScope.() -> Unit) {
    RecordingTailwindScope(selectors + name, properties).scope()
  }
}

/** An implementation of [TailwindStyles]. */
private class TailwindStylesImpl : TailwindStyles {

  private val all = mutableStateMapOf<String, TailwindProperties>()

  @Composable
  override fun properties(properties: TailwindProperties): String {
    val name = remember(properties) { "tailwind-${properties.hashCode()}-${Random.nextInt()}" }
    DisposableEffect(properties) {
      all[name] = properties
      onDispose { all.remove(name) }
    }
    return name
  }

  @Composable
  override fun StyleSheet() {
    Style {
      all.forEach { (name, properties) ->
        val grouped = properties.properties.groupBy { it.selectors }
        for ((classes, props) in grouped) {
          val pseudoClasses = classes.map(::type)
          val selector = combine(className(name), *pseudoClasses.toTypedArray())
          style(selector) { props.forEach { (_, name, value) -> property(name, value) } }
        }
      }
    }
  }
}
