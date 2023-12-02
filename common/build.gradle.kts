plugins {
  kotlin("multiplatform") apply true
  kotlin("plugin.serialization") apply true
}

kotlin {
  jvm()
  js(IR) { browser() }
  sourceSets {
    val commonMain by getting {
      dependencies {
        api("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
        api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

        // Ktor (api.ktor.io)
        api("io.ktor:ktor-client-auth:2.3.6")
        api("io.ktor:ktor-client-content-negotiation:2.3.6")
        api("io.ktor:ktor-client-json:2.3.6")
        api("io.ktor:ktor-client-serialization:2.3.6")
        api("io.ktor:ktor-client-websockets:2.3.6")
        api("io.ktor:ktor-serialization-kotlinx-json:2.3.6")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
      }
    }
  }
}
