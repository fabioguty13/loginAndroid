package com.example.loginandroid.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Componente reutilizable para mostrar el estado de carga en la aplicación.
 */
@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

/**
 * Barra superior reutilizable para navegacion.
 * 
 * @param title Texto del título de la pantalla.
 * @param onHomeClick Acción para navegar al home.
 * @param onMenuClick Acción para abrir el menú de categorías.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onHomeClick: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if (onHomeClick != null) {
                IconButton(onClick = onHomeClick) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Inicio")
                }
            }
        },
        actions = {
            if (onMenuClick != null) {
                IconButton(onClick = onMenuClick) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Categorías")
                }
            }
        }
    )
}
