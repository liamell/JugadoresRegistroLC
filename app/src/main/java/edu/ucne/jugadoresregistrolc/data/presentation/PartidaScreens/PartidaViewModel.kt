package edu.ucne.jugadoresregistrolc.data.presentation.PartidaScreens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity
import edu.ucne.jugadoresregistrolc.data.entities.PartidaEntity
import edu.ucne.jugadoresregistrolc.data.repositories.JugadorRepository
import edu.ucne.jugadoresregistrolc.data.repositories.PartidaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PartidaViewModel @Inject constructor(
    private val repository: PartidaRepository,
    private val jugadorRepository: JugadorRepository

) : ViewModel() {
    private val _uiState = MutableStateFlow(JugadorUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getJugadores()
    }

     fun guardarJugador(){
        viewModelScope.launch {
            jugadorRepository.save(_uiState.value.toJugadorEntity())
        }
    }
    private fun getJugadores(){
        viewModelScope.launch {
            try {
                jugadorRepository.getAll().collect{jugadores ->
                    _uiState.update {
                        it.copy(listajugadores = jugadores)
                    }
                }
            } catch (e: Exception){
                Log.e("ViewModel", "Error obteniendo cuentas: ${e.message}", e)
                e.printStackTrace()
            }
        }
    }
    val partidas = repository.getAll()

        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    fun insertPartida(jugador1Id: Int, jugador2Id: Int, ganadorId: Int? = null) {
        viewModelScope.launch {
            val fecha = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
            val partida = PartidaEntity(
                fecha = fecha,
                jugador1Id = jugador1Id,
                jugador2Id = jugador2Id,
                ganadorId = ganadorId,
                esFinalizada = ganadorId != null
            )
            repository.insert(partida)
        }
    }

    data class JugadorUiState(
        val jugadorId: Int? = null,
        val nombre: String = "",
        val partidas: Int = 0,
        val listajugadores: List<JugadorEntity> = emptyList()
    )

    fun updatePartida(partida: PartidaEntity) {
        viewModelScope.launch {
            repository.update(partida)
        }
    }

    fun deletePartida(partida: PartidaEntity) {
        viewModelScope.launch {
            repository.delete(partida)
        }
    }

    fun finalizarPartida(partidaId: Int, ganadorId: Int) {
        viewModelScope.launch {
            val partida = repository.getById(partidaId)
            partida?.let {
                val partidaActualizada = it.copy(
                    ganadorId = ganadorId,
                    esFinalizada = true
                )
                repository.update(partidaActualizada)
            }
        }
    }
    fun JugadorUiState.toJugadorEntity() = JugadorEntity(
        jugadorId = jugadorId,
        nombre = nombre,
        partidas = partidas
    )



    fun onEvent(event: JugadorUiEvent ){
        when (event){
            is JugadorUiEvent.NombreChanged -> {
                _uiState.update {
                    it.copy(nombre = event.nombre)
                }
            }
            is JugadorUiEvent.PartidaChange -> {
                _uiState.update {
                    it.copy(partidas = event.partida)
                }
            }
            JugadorUiEvent.Save -> {
                viewModelScope.launch {
                    jugadorRepository.save(_uiState.value.toJugadorEntity())
                }
            }
        }
    }
}


