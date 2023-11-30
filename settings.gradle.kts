pluginManagement {
  repositories {
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
  }

  plugins {
    val kotlinVersion = "1.9.21"
    val composeVersion = "1.5.11"

    kotlin("jvm").version(kotlinVersion)
    kotlin("multiplatform").version(kotlinVersion)
    kotlin("plugin.serialization").version(kotlinVersion)
    id("org.jetbrains.compose").version(composeVersion)
  }
}

plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0" }

rootProject.name = "lauzhack-template"

include(":backend")

include(":frontend")

include(":common")
