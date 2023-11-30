plugins {
  kotlin("jvm") apply true
  application
}

dependencies { project(":common") }

application { mainClass = "io.github.lauzhack.backend.Main" }
