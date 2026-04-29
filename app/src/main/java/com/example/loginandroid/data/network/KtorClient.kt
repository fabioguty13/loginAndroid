package com.example.loginandroid.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Cliente HTTP único de la app. El token JWT se lee en cada request a través
 * de [tokenProvider], que MainActivity configura en arranque para apuntar al SessionManager.
 */
object KtorClient {
    var tokenProvider: () -> String? = { null }

    val instance: HttpClient by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            defaultRequest {
                tokenProvider()?.takeIf { it.isNotBlank() }?.let { token ->
                    header(HttpHeaders.Authorization, "Bearer $token")
                }
            }
        }
    }
}

val client: HttpClient get() = KtorClient.instance
