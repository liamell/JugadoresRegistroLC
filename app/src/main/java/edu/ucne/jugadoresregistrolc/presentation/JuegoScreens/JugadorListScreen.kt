package edu.ucne.jugadoresregistrolc.presentation.JuegoScreens

import JugadorViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity
@Composable
fun JugadorListScreen(
    //goJugadorScreen: (Int) -> Unit,
    jugadorViewModel: JugadorViewModel = hiltViewModel()
) {
    val uiState by jugadorViewModel.uiState.collectAsState()
    JugadorListBodyScreen(
        uiState = uiState,
        //goJugadorScreen = { goJugadorScreen(0) }
    )
}

@Composable
private fun JugadorListBodyScreen(
    uiState: JugadorViewModel.JugadorUiState,
    //goJugadorScreen: (Int) -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text("Lista de jugadores")

            if(uiState.listajugadores.isEmpty()){
                Text("No hay jugadores")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.listajugadores) {
                        JugadorRow(it)
                    }
                }
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