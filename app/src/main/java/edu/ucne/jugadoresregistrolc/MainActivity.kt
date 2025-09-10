package edu.ucne.jugadoresregistrolc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import edu.ucne.jugadoresregistrolc.data.database.JugadorDb
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity
import edu.ucne.jugadoresregistrolc.data.presentation.JugadorListScreen
import edu.ucne.jugadoresregistrolc.data.repositories.JugadorRepository
import edu.ucne.jugadoresregistrolc.data.presentation.JugadorScreen
import edu.ucne.jugadoresregistrolc.ui.theme.JugadoresRegistroLCTheme




class MainActivity : ComponentActivity() {
    private lateinit var jugadorRepository: JugadorRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val jugadorDb = Room.databaseBuilder(
            applicationContext,
            JugadorDb::class.java,
            "Jugadores.db"
        ).fallbackToDestructiveMigration()
            .build()
        jugadorRepository = JugadorRepository(jugadorDb.jugadorDao())

        setContent {
            JugadoresRegistroLCTheme { // Aquí se cierra correctamente solo con una llave al final del tema
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        JugadorScreen(jugadorRepository)
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        JugadoresRegistroLCTheme {
            val jugadorList = listOf(
                JugadorEntity(1, "L i a mell", 15112003),
            )
            JugadorListScreen(jugadorList)
        }
    }
}