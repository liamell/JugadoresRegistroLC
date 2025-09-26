package edu.ucne.jugadoresregistrolc.presentation.PartidaScreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.jugadoresregistrolc.data.entities.PartidaEntity
import edu.ucne.jugadoresregistrolc.data.repositories.JugadorRepository
import edu.ucne.jugadoresregistrolc.data.repositories.PartidaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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


    init {
        //getJugadores()
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
}