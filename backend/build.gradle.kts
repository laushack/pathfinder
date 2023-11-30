plugins {
  kotlin("jvm") apply true
  application
}

application { mainClass = "io.github.lauzhack.backend.Main" }

dependencies {
  implementation(project(":common"))
  // The list of Ktor modules is available on https://api.ktor.io.
  implementation("io.ktor:ktor-server-core:2.3.6")
  implementation("io.ktor:ktor-server-cio:2.3.6")
  implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.6")
  implementation("io.ktor:ktor-server-websockets:2.3.6")
}
