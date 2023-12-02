package io.github.lauzhack.common.api

import kotlinx.serialization.Serializable

val DefaultJsonSerializer =
    kotlinx.serialization.json.Json {
      ignoreUnknownKeys = true
      isLenient = true
      encodeDefaults = true
      prettyPrint = true
      classDiscriminator = "type"
    }

@Serializable sealed class BackendToUserMessage

@Serializable sealed class UserToBackendMessage
