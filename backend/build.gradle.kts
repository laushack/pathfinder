plugins {
  kotlin("jvm") apply true
  kotlin("plugin.serialization") apply true
  application
}

application { mainClass = "io.github.lauzhack.backend.Main" }

dependencies {
  implementation(project(":common"))

  // Ktor (api.ktor.io)
  implementation("io.ktor:ktor-client-cio:2.3.6")
  implementation("io.ktor:ktor-server-core:2.3.6")
  implementation("io.ktor:ktor-server-cors:2.3.6")
  implementation("io.ktor:ktor-server-cio:2.3.6")
  implementation("io.ktor:ktor-server-content-negotiation:2.3.6")
  implementation("io.ktor:ktor-server-websockets:2.3.6")

  // OpenCSV
  implementation("com.opencsv:opencsv:5.5.2")
}
