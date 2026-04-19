package com.example.loginandroid.login

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.loginandroid.screens.categories.CategoriesScreen
import com.example.loginandroid.screens.products.ProductScreen

@Composable
fun LoginWrapper(){

    val backStack = rememberNavBackStack(Routes.Categories)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider
        {
            entry<Routes.Categories>{
                CategoriesScreen(
                    onNavegarProductos = { backStack.add(Routes.Products) }
                )
            }
            entry<Routes.Products>{
                ProductScreen()
            }
        }
    )
}

