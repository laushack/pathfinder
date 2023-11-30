plugins {
  kotlin("multiplatform") apply true
  kotlin("plugin.serialization") apply true
}

kotlin {
  js(IR) { browser() }
  sourceSets {
    val commonMain by getting {
      dependencies { implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1") }
    }
  }
}
