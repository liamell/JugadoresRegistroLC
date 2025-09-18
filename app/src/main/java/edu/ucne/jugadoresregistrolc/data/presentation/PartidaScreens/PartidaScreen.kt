package edu.ucne.jugadoresregistrolc.data.presentation.PartidaScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.jugadoresregistrolc.data.presentation.PartidaScreens.PartidaViewModel


@Composable
fun PartidaScreen(
    viewModel: PartidaViewModel = hiltViewModel(),
    onNavigateToCreate: () -> Unit,
    onNavigateToEdit: (Int) -> Unit
) {
    val partidas by viewModel.partidas.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreate) {
                Icon(Icons.Default.Add, contentDescription = "Crear partida")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(
                text = "Lista de Partidas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(partidas) { partida ->
                    PartidaItem(
                        partida = partida,
                        onEdit = { onNavigateToEdit(partida.partidaId) },
                        onDelete = { viewModel.deletePartida(partida) }
                    )
                }
            }
        }
    }
}



