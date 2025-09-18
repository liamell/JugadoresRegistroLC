// PartidaScreens/PartidaItem.kt
package edu.ucne.jugadoresregistrolc.data.presentation.PartidaScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.ucne.jugadoresregistrolc.data.entities.PartidaEntity

@Composable
fun PartidaItem(
    partida: PartidaEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onEdit() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Fecha: ${partida.fecha}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Jugador 1: ${partida.jugador1Id}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Jugador 2: ${partida.jugador2Id}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Ganador: ${partida.ganadorId ?: "Pendiente"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = if (partida.esFinalizada) "Finalizada" else "En curso",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (partida.esFinalizada) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }

            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Editar partida")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar partida")
            }
        }
    }
}