// MainActivity.kt
package edu.ucne.jugadoresregistrolc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import edu.ucne.jugadoresregistrolc.data.database.JugadorDb
import edu.ucne.jugadoresregistrolc.data.repositories.JugadorRepository
import edu.ucne.jugadoresregistrolc.data.repositories.PartidaRepository
import edu.ucne.jugadoresregistrolc.navigation.AppNavHost
import edu.ucne.jugadoresregistrolc.ui.theme.JugadoresRegistroLCTheme

class MainActivity : ComponentActivity() {
    private lateinit var jugadorRepository: JugadorRepository
    private lateinit var partidaRepository: PartidaRepository

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
        partidaRepository = PartidaRepository(jugadorDb.partidaDao())

        setContent {
            JugadoresRegistroLCTheme {
                val navController = rememberNavController()

                AppNavHost(
                    navController = navController,
                    jugadorRepository = jugadorRepository,
                    partidaRepository = partidaRepository
                )
            }
        }
    }
}