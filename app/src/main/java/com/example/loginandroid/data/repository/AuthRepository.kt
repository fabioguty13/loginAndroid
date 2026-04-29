package com.example.loginandroid.data.repository

import com.example.loginandroid.data.SessionManager
import com.example.loginandroid.data.network.client
import com.example.loginandroid.data.network.model.LoginRequest
import com.example.loginandroid.data.network.model.LoginResponse
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class AuthRepository(private val sessionManager: SessionManager) {

    suspend fun login(email: String, password: String): Result<String> = runCatching {
        val response = client.post(LOGIN_URL) {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(email = email, password = password))
        }
        when (response.status) {
            HttpStatusCode.Created, HttpStatusCode.OK -> {
                val token = response.body<LoginResponse>().accessToken
                sessionManager.saveSession(token = token, email = email)
                token
            }
            HttpStatusCode.Unauthorized -> error("Credenciales incorrectas")
            else -> error("Error del servidor (${response.status.value}): ${response.bodyAsText()}")
        }
    }

    fun logout() {
        sessionManager.clear()
    }

    companion object {
        private const val LOGIN_URL = "https://api.escuelajs.co/api/v1/auth/login"
    }
}
