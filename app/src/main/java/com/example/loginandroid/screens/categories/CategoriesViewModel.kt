package com.example.loginandroid.screens.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginandroid.data.network.model.Category
import com.example.loginandroid.data.repository.CategoryRepository
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lógica de negocio para las categorías.
 */
class CategoriesViewModel : ViewModel() {
    private val repository = CategoryRepository()

    var categories by mutableStateOf<List<Category>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        loadCategories()
    }

    /**
     * Carga las categorías desde el repositorio y filtra aquellas que son inválidos al ser un api publica cualquiera puede crear categorias.
     */
    fun loadCategories() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val fetchedCategories = repository.getCategories()
                // Filtrar categorías con nombres sospechosos como "category_B" o "string" que en nuestras pruebas esstaba pasando
                categories = fetchedCategories.filter { category ->
                    category.name.lowercase() != "string" && 
                    category.name.lowercase() != "category_b" &&
                    !category.name.contains("test", ignoreCase = true)
                }
            } catch (e: Exception) {
                errorMessage = "Error al cargar categorías: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
