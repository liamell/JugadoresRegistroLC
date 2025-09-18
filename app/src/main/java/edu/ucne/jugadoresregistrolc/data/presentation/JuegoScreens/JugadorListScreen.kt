package edu.ucne.jugadoresregistrolc.data.presentation.JuegoScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity
import edu.ucne.jugadoresregistrolc.data.presentation.PartidaScreens.PartidaViewModel


@Composable
fun JugadorListScreen(partidaViewModel: PartidaViewModel = hiltViewModel()) {
    val uiState by partidaViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("Lista de jugadores")
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(uiState.listajugadores) {
                JugadorRow(it)
            }
        }
    }
}

@Composable
private fun JugadorRow(it: JugadorEntity) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(1f), text = it.jugadorId.toString())
        Text(
            modifier = Modifier.weight(2f),
            text = it.nombre,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(modifier = Modifier.weight(2f), text = it.partidas.toString())
    }
    HorizontalDivider()
}