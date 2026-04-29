package com.example.loginandroid.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.loginandroid.data.SessionManager
import com.example.loginandroid.screens.categories.CategoriesScreen
import com.example.loginandroid.screens.home.HomeScreen
import com.example.loginandroid.screens.login.LoginScreen
import com.example.loginandroid.screens.products.ProductDetailScreen
import com.example.loginandroid.screens.products.ProductScreen

/**
 * Gestor de navegación principal de la aplicación.
 * Decide entre Login y Home según si hay un token guardado.
 */
@Composable
fun LoginWrapper() {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    val startRoute: Routes = if (sessionManager.isLoggedIn()) Routes.Home else Routes.Login
    val backStack = rememberNavBackStack(startRoute)

    val navigateToHome: () -> Unit = {
        while (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    val navigateToCategories: () -> Unit = {
        backStack.add(Routes.Categories)
    }

    val onLoginSuccess: () -> Unit = {
        backStack.add(Routes.Home)
        // Quitar Login y cualquier pantalla previa, dejando solo Home en el stack.
        while (backStack.size > 1) {
            backStack.removeAt(0)
        }
    }

    val onLogout: () -> Unit = {
        sessionManager.clear()
        backStack.add(Routes.Login)
        while (backStack.size > 1) {
            backStack.removeAt(0)
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Login> {
                LoginScreen(onLoginSuccess = onLoginSuccess)
            }
            entry<Routes.Home> {
                HomeScreen(
                    onNavigateToMenu = navigateToCategories,
                    onLogout = onLogout
                )
            }
            entry<Routes.Categories> {
                CategoriesScreen(
                    onCategoryClick = { categoryId ->
                        backStack.add(Routes.Products(categoryId))
                    },
                    onNavigateToHome = navigateToHome,
                    onNavigateToMenu = navigateToCategories,
                    onLogout = onLogout
                )
            }
            entry<Routes.Products> { route ->
                ProductScreen(
                    categoryId = route.categoryId,
                    onProductClick = { productId ->
                        backStack.add(Routes.ProductDetail(productId))
                    },
                    onNavigateToHome = navigateToHome,
                    onNavigateToMenu = navigateToCategories,
                    onLogout = onLogout
                )
            }
            entry<Routes.ProductDetail> { route ->
                ProductDetailScreen(
                    productId = route.productId,
                    onNavigateToHome = navigateToHome,
                    onNavigateToMenu = navigateToCategories,
                    onLogout = onLogout
                )
            }
        }
    )
}
