package com.example.loginandroid.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.loginandroid.data.SessionManager
import com.example.loginandroid.ui.components.AppTopBar

/**
 * Pantalla de inicio. Saluda al usuario logueado y presenta a los integrantes del grupo.
 */
@Composable
fun HomeScreen(
    onNavigateToMenu: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val email = remember { SessionManager(context).getEmail() }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Inicio",
                onMenuClick = onNavigateToMenu,
                onLogoutClick = onLogout
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!email.isNullOrBlank()) {
                        Text(
                            text = "Hola,",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = email,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.height(16.dp))
                        HorizontalDivider()
                        Spacer(Modifier.height(16.dp))
                    }
                    Text(
                        text = "Bienvenido a la Tienda Grupo 4",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Explora nuestras categorías tocando el icono de menú en la parte superior derecha.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Esta aplicación fue desarrollada por:",
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            val integrantes = listOf(
                "• Héctor Fabio Gutierrez Martinez",
                "• David Sebastian Montes Zarama",
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
