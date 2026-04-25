package com.example.loginandroid.login

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.loginandroid.screens.categories.CategoriesScreen
import com.example.loginandroid.screens.products.ProductScreen
import com.example.loginandroid.screens.products.ProductDetailScreen
import com.example.loginandroid.screens.home.HomeScreen

/**
 * Gestor de navegación principal de la aplicación.
 * Define las pantallas disponibles y las transiciones entre ellas.
 */
@Composable
fun LoginWrapper() {
    val backStack = rememberNavBackStack(Routes.Home)

    val navigateToHome: () -> Unit = {
        while (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }
    
    val navigateToCategories: () -> Unit = {
        backStack.add(Routes.Categories)
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            // Pantalla de Inicio
            entry<Routes.Home> {
                HomeScreen(
                    onNavigateToMenu = navigateToCategories
                )
            }
            // Pantalla de Menú de Categorías
            entry<Routes.Categories> {
                CategoriesScreen(
                    onCategoryClick = { categoryId -> 
                        backStack.add(Routes.Products(categoryId)) 
                    },
                    onNavigateToHome = navigateToHome,
                    onNavigateToMenu = navigateToCategories
                )
            }
            // Pantalla de Listado de Productos
            entry<Routes.Products> { route ->
                ProductScreen(
                    categoryId = route.categoryId,
                    onProductClick = { productId ->
                        backStack.add(Routes.ProductDetail(productId))
                    },
                    onNavigateToHome = navigateToHome,
                    onNavigateToMenu = navigateToCategories
                )
            }
            // Pantalla de Detalle de Producto
            entry<Routes.ProductDetail> { route ->
                ProductDetailScreen(
                    productId = route.productId,
                    onNavigateToHome = navigateToHome,
                    onNavigateToMenu = navigateToCategories
                )
            }
        }
    )
}
