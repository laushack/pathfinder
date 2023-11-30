plugins {
  kotlin("multiplatform") apply true
  id("org.jetbrains.compose") apply true
}

kotlin {
  js(IR) { browser() }
  sourceSets {
    val jsMain by getting {
      dependencies {
        implementation(project(":common"))
        implementation(compose.runtime)
        implementation(compose.html.core)
      }
    }
  }
}
