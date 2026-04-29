package com.example.loginandroid.data

import android.content.Context
import android.content.SharedPreferences

/**
 * Gestiona la sesión del usuario en disco usando SharedPreferences.
 * Guarda el JWT recibido del login y, opcionalmente, el email para mostrarlo en la UI.
 */
class SessionManager(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveSession(token: String, email: String) {
        prefs.edit()
            .putString(KEY_TOKEN, token)
            .putString(KEY_EMAIL, email)
            .apply()
    }

    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)

    fun isLoggedIn(): Boolean = !getToken().isNullOrBlank()

    fun clear() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "auth_prefs"
        private const val KEY_TOKEN = "jwt_token"
        private const val KEY_EMAIL = "user_email"
    }
}
