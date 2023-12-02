package io.github.lauzhack.backend.api.openAI

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

/** The API key for the OpenAI API. */
private val OpenAIApiKey =
    System.getenv("OPENAI_API_KEY") ?: error("OPENAI_API_KEY environment variable not set")

/** Creates an HTTP client for the OpenAI API. */
private fun httpClient(key: String) =
    HttpClient(CIO) {
      defaultRequest {
        url {
          protocol = URLProtocol.HTTPS
          host = "api.openai.com"
        }
      }
      install(ContentNegotiation) { json() }
      install(Auth) { bearer { loadTokens { BearerTokens(key, key) } } }
    }

/** A service for the OpenAI API. */
class OpenAIService(apiKey: String = OpenAIApiKey) {
  private val client = httpClient(apiKey)

  /** Sends a prompt to the OpenAI API and returns the response. */
  suspend fun prompt(request: OpenAIRequest): OpenAIResponse {
    val response =
        client.post("/v1/chat/completions") {
          contentType(ContentType.Application.Json)
          setBody(request)
        }
    return response.body()
  }
}
