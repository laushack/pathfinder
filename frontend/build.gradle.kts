plugins {
  kotlin("multiplatform") apply true
  id("org.jetbrains.compose") apply true
}

kotlin {
  js(IR) {
    browser()
    binaries.executable()
  }
  sourceSets {
    val jsMain by getting {
      dependencies {
        implementation(project(":common"))
        implementation(compose.runtime)
        implementation(compose.html.core)
        implementation(compose.html.svg)

        // Ktor (api.ktor.io)
        implementation("io.ktor:ktor-client-js:2.3.6")
      }
    }
  }
}
