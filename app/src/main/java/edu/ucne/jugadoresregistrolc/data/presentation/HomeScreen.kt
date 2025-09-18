// presentation/HomeScreen.kt
package edu.ucne.jugadoresregistrolc.data.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToJugadores: () -> Unit,
    onNavigateToPartidas: () -> Unit,

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onNavigateToJugadores,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Gestionar Jugadores")
        }

        Button(
            onClick = onNavigateToPartidas,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Gestionar Partidas")
        }


    }
}