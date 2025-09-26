package edu.ucne.jugadoresregistrolc.presentation.JuegoScreens

sealed interface JugadorUiEvent {
    data class NombreChanged(val nombre: String): JugadorUiEvent
    data class PartidaChange(val partida: Int): JugadorUiEvent
    data object Save: JugadorUiEvent

}