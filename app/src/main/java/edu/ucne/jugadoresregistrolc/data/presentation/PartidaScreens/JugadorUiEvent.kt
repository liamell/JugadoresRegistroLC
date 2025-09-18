package edu.ucne.jugadoresregistrolc.data.presentation.PartidaScreens

sealed interface JugadorUiEvent {
    data class NombreChanged(val nombre: String): JugadorUiEvent
    data class PartidaChange(val partida: Int): JugadorUiEvent
    data object Save: JugadorUiEvent

}