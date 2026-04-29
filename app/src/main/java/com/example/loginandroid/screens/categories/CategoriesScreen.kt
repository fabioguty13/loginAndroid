package com.example.loginandroid.screens.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.loginandroid.data.network.model.Category
import com.example.loginandroid.ui.components.AppTopBar
import com.example.loginandroid.ui.components.LoadingView

/**
 * Pantalla que muestra el menú de categorías para que el usuario seleccione una.
 */
@Composable
fun CategoriesScreen(
    onCategoryClick: (Int) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToMenu: () -> Unit,
    onLogout: () -> Unit,
    categoriesViewModel: CategoriesViewModel = viewModel()
) {
    val categoriesList = categoriesViewModel.categories
    val isFetching = categoriesViewModel.isLoading
    val error = categoriesViewModel.errorMessage

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Categorías",
                onHomeClick = onNavigateToHome,
                onMenuClick = onNavigateToMenu,
                onLogoutClick = onLogout
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when {
                isFetching -> LoadingView()
                error != null -> {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    CategoryList(
                        categories = categoriesList,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryList(
    categories: List<Category>,
    onCategoryClick: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { category ->
            CategoryCard(
                category = category,
                onClick = { onCategoryClick(category.id) }
            )
        }
    }
}

@Composable
fun CategoryCard(
    category: Category,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = category.image,
                contentDescription = category.name,
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
