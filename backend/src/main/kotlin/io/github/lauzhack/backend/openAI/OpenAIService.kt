package io.github.lauzhack.backend.openAI

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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** The API key for the OpenAI API. */
private val OpenAIApiKey =
    System.getenv("OPENAI_API_KEY") ?: error("OPENAI_API_KEY environment variable not set")

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

class OpenAIService(apiKey: String = OpenAIApiKey) {
  private val client = httpClient(apiKey)

  suspend fun prompt(request: OpenAIRequest): OpenAIResponse {
    val response =
        client.post("/v1/chat/completions") {
          contentType(ContentType.Application.Json)
          setBody(request)
        }
    return response.body()
  }
}

@Serializable
data class OpenAIRequest(
    @SerialName("model") val model: String = "gpt-3.5-turbo",
    @SerialName("messages") val messages: List<OpenAIMessage>,
)

@Serializable
data class OpenAIResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val obj: String,
    @SerialName("created") val created: Long,
    @SerialName("model") val model: String,
    @SerialName("choices") val choices: List<OpenAIChoice>,
    @SerialName("usage") val usage: OpenAIUsage,
    @SerialName("system_fingerprint") val systemFingerprint: String?,
)

@Serializable
data class OpenAIChoice(
    @SerialName("index") val index: Int,
    @SerialName("message") val message: OpenAIMessage,
    @SerialName("finish_reason") val finishReason: String,
)

@Serializable
data class OpenAIMessage(
    @SerialName("role") val role: String,
    @SerialName("content") val content: String,
)

@Serializable
data class OpenAIUsage(
    @SerialName("prompt_tokens") val promptTokens: Int,
    @SerialName("completion_tokens") val completionTokens: Int,
    @SerialName("total_tokens") val totalTokens: Int,
)
