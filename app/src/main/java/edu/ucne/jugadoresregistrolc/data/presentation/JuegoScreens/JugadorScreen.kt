package edu.ucne.jugadoresregistrolc.data.presentation.JuegoScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.jugadoresregistrolc.data.presentation.PartidaScreens.JugadorUiEvent
import edu.ucne.jugadoresregistrolc.data.presentation.PartidaScreens.PartidaViewModel
import kotlinx.coroutines.launch

@Composable
fun JugadorScreen(
   viewModel: PartidaViewModel = hiltViewModel(),
    onBack: () -> Unit,
    jugadorId: Int?
) {
    val uiState by viewModel.uiState.collectAsState()
    JugadorBodyScreen(
        uiState = uiState,
        onEvent = viewModel:: onEvent,
        onBack = onBack

    )
}
@Composable
fun JugadorBodyScreen(
    uiState: PartidaViewModel.JugadorUiState,
    onEvent: (JugadorUiEvent) -> Unit,
    onBack:  () -> Unit
){
    var nombre by remember { mutableStateOf("") }
    var partidas by remember { mutableStateOf("") }
    var errorMessage: String? by remember { mutableStateOf(null) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    OutlinedTextField(
                        label = { Text(text = "Nombre") },
                        value = uiState.nombre,
                        onValueChange = { onEvent(JugadorUiEvent.NombreChanged(it)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        label = { Text(text = "Partidas") },
                        value = partidas,
                        onValueChange = { partidas = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    errorMessage?.let {
                        Text(text = it, color = Color.Red)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(onClick = {
                            nombre = ""
                            partidas = ""
                            errorMessage = ""
                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "New button"
                            )
                            Text(text = "Nuevo")
                        }
                        val scope = rememberCoroutineScope()
                        OutlinedButton(
                            onClick = {
                                if (nombre.isBlank() || partidas.isEmpty()) {
                                    errorMessage = "No se puede guardar con datos vacíos"
                                    return@OutlinedButton
                                }
                                if (partidas.toInt() <= 0) {
                                    errorMessage = "La cantidad debe ser mayor que 0"
                                    return@OutlinedButton
                                }
                                scope.launch {
                                    onEvent(JugadorUiEvent.Save)

//                                    jugadorRepository.save(
//                                        JugadorEntity(
//                                            nombre = nombre,
//                                            partidas = partidas.toInt()
//                                        )
//                                    )
//                                    nombre = ""
//                                    partidas = ""
//                                    errorMessage = ""
                                }
                            }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Save button"
                            )
                            Text(text = "Guardar")
                        }
                    }
                }
            }
        }
    }
}