package com.example.loginandroid.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.loginandroid.ui.components.AppTopBar

/**
 * Pantalla de inicio de la aplicación
 * 
 * Aqui se proporciona un saludo al usuario y el nombre de los integrantes del grupo
 */
@Composable
fun HomeScreen(
    onNavigateToMenu: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Inicio",
                onMenuClick = onNavigateToMenu
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido a la Tienda Grupo4",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Explora nuestras categorías tocando el icono de menú en la parte superior derecha.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 32.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Esta aplicación fue desarrollada por:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 32.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            val integrantes = listOf(
                "• Héctor Fabio Gutierrez Martinez",
                "• David Sebastian montes Zarama",
                "• Vanessa Hernández Maldonado"
            )
            integrantes.forEach { nombre ->
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
