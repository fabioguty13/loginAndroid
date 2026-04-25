package com.example.loginandroid.screens.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginandroid.data.network.model.Product
import com.example.loginandroid.data.repository.ProductRepository
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de la lógica de negocio para los productos.
 */
class ProductViewModel : ViewModel() {
    private val productRepository = ProductRepository()

    /**
     * Lista de productos a mostrar.
     */
    var productList by mutableStateOf<List<Product>>(emptyList())
        private set

    /**
     * Indica si se está realizando carga de datos.
     */
    var isDataLoading by mutableStateOf(true)
        private set

    /**
     * Guarda el mensaje de error en caso de fallos.
     */
    var networkErrorMessage by mutableStateOf<String?>(null)
        private set

    /**
     * Obtiene la lista de productos desde el repositorio.
     * 
     * @param categoryId Identificador opcional para filtrar los productos.
     */
    fun fetchProducts(categoryId: Int? = null) {
        viewModelScope.launch {
            isDataLoading = true
            networkErrorMessage = null
            try {
                productList = productRepository.getProducts(categoryId)
            } catch (e: Exception) {
                networkErrorMessage = "Error al obtener los productos: ${e.localizedMessage}"
            } finally {
                isDataLoading = false
            }
        }
    }
}
