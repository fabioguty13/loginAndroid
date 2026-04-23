package com.example.loginandroid.data.repository

import com.example.loginandroid.data.network.client
import com.example.loginandroid.data.network.model.Product
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Repositorio encargado de la gestión de datos relacionados con los productos.
 * 
 * Centraliza el acceso a la fuente de datos (API externa) para la entidad [Product].
 */
class ProductRepository {

    /**
     * Obtiene la lista completa de productos o filtrada por categoría desde el servicio remoto.
     * 
     * @param categoryId Identificador opcional de la categoría para filtrar.
     * @return Una lista de objetos [Product].
     * @throws Exception Si ocurre un error en la comunicación con la API.
     */
    suspend fun getProducts(categoryId: Int? = null): List<Product> {
        val url = if (categoryId != null) {
            "https://api.escuelajs.co/api/v1/categories/$categoryId/products"
        } else {
            "https://api.escuelajs.co/api/v1/products"
        }
        return client.get(url).body()
    }

    /**
     * Obtiene un producto específico por su identificador único.
     * 
     * @param productId ID del producto a recuperar.
     * @return El objeto [Product] correspondiente.
     */
    suspend fun getProductById(productId: Int): Product {
        return client.get("https://api.escuelajs.co/api/v1/products/$productId").body()
    }
}
