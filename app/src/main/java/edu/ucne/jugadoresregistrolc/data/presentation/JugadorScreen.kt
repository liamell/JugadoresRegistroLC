package edu.ucne.jugadoresregistrolc.data.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity
import edu.ucne.jugadoresregistrolc.data.repositories.JugadorRepository
import kotlinx.coroutines.launch

@Composable
fun JugadorScreen(
    jugadorRepository: JugadorRepository
) {
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
                        value = nombre,
                        onValueChange = { nombre = it },
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
                                    jugadorRepository.save(
                                        JugadorEntity(
                                            nombre = nombre,
                                            partidas = partidas.toInt()
                                        )
                                    )
                                    nombre = ""
                                    partidas = ""
                                    errorMessage = ""
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
            val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
            val jugadorList by jugadorRepository.getAll()
                .collectAsStateWithLifecycle(
                    initialValue = emptyList(),
                    lifecycleOwner = lifecycleOwner,
                    minActiveState = Lifecycle.State.STARTED
                )
            JugadorListScreen(jugadorList)
        }
    }
}