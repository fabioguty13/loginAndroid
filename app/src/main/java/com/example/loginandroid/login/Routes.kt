package com.example.loginandroid.login

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Definición de las rutas de navegación de la aplicación.
 */
sealed class Routes : NavKey {
    @Serializable
    data object Login : Routes()

    @Serializable
    data object Home : Routes()

    @Serializable
    data object Categories : Routes()

    @Serializable
    data class Products(val categoryId: Int? = null) : Routes()

    @Serializable
    data class ProductDetail(val productId: Int) : Routes()
}
