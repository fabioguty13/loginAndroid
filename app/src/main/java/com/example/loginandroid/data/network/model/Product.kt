package com.example.loginandroid.data.network.model

import kotlinx.serialization.Serializable

/**
 * Representa un producto obtenido de la API de la tienda.
 * 
 * @property id Identificador único del producto.
 * @property title Nombre o título del producto.
 * @property price Precio del producto en formato numérico.
 * @property description Descripción detallada del producto.
 * @property category Categoría a la que pertenece el producto.
 * @property images Lista de URLs de las imágenes asociadas al producto.
 */
@Serializable
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: Category,
    val images: List<String>
)
