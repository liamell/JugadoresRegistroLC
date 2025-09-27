// Navigation/AppNavHost.kt
package edu.ucne.jugadoresregistrolc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.jugadoresregistrolc.presentation.Navigation.Screen
import edu.ucne.jugadoresregistrolc.presentation.HomeScreen
import edu.ucne.jugadoresregistrolc.presentation.JuegoScreens.JugadorScreen
import edu.ucne.jugadoresregistrolc.presentation.JuegoScreens.JugadorListScreen
import edu.ucne.jugadoresregistrolc.presentation.PartidaScreens.CreatePartidaScreen
import edu.ucne.jugadoresregistrolc.presentation.PartidaScreens.PartidaListScreen
import edu.ucne.jugadoresregistrolc.data.repositories.JugadorRepository
import edu.ucne.jugadoresregistrolc.data.repositories.PartidaRepository

@Composable
fun AppNavHost(
    navController: NavHostController,
    jugadorRepository: JugadorRepository,
    partidaRepository: PartidaRepository
) {

    NavHost(
        navController = navController,
        // Usamos la clase de la ruta como startDestination
        startDestination = Screen.HomeScreen
    ) {
        // Pantalla de Inicio
        composable<Screen.HomeScreen> {
            HomeScreen(
                onNavigateToJugadores = { navController.navigate(Screen.JugadorList) },
                onNavigateToPartidas = { navController.navigate(Screen.PartidaList) }
            )
        }

        // Pantallas de Jugadores
        composable<Screen.JugadorList> {
            // Ya no necesitas pasar el repositorio, la pantalla lo obtiene del ViewModel
            JugadorListScreen(

//                onNavigateToCreate = { navController.navigate(Screen.Jugador(0)) },
//                onNavigateToEdit = { id -> navController.navigate(Screen.Jugador(id)) },

            )
        }

        // Uso de toRoute para obtener el argumento de forma segura
        composable<Screen.Jugador> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.Jugador>()
            val jugadorId = args.jugadorId

            JugadorScreen(
                jugadorId = if (jugadorId == 0) null else jugadorId,
                onBack = { navController.navigate(Screen.JugadorList) }
            )
        }

        // Pantallas de Partidas
        composable<Screen.PartidaList> {
            // Elimina el repositorio y deja que el ViewModel lo maneje
            PartidaListScreen(
                onNavigateToCreate = { navController.navigate(Screen.CreatePartida) },
                onNavigateToEdit = { id -> navController.navigate(Screen.Partida(id)) }
            )
        }

        composable<Screen.CreatePartida> {
            CreatePartidaScreen(
                // Elimina el repositorio de los parámetros
                onBack = { navController.popBackStack() }
            )
        }

        // Uso de toRoute para obtener el argumento de forma segura
        composable<Screen.Partida> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.Partida>()
            val partidaId = args.partidaId

            CreatePartidaScreen(
                onBack = { navController.navigate(Screen.JugadorList) }
            )
        }


    }
}