import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity
import edu.ucne.jugadoresregistrolc.data.repositories.JugadorRepository
import edu.ucne.jugadoresregistrolc.presentation.JuegoScreens.JugadorUiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadorViewModel @Inject constructor(
    private val repository: JugadorRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(JugadorUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getJugadores()
    }

    fun guardarJugador(){
        viewModelScope.launch {
            repository.save(_uiState.value.toJugadorEntity())
        }
    }
    private fun getJugadores(){
        viewModelScope.launch {
            try {
                repository.getAll().collect{jugadores ->
                    _uiState.update {
                        it.copy(listajugadores = jugadores)
                    }
                }
            } catch (e: Exception){
                Log.e("ViewModel", "Error obteniendo jugadores: ${e.message}", e)
                e.printStackTrace()
            }
        }
    }

    data class JugadorUiState(
        val jugadorId: Int? = null,
        val nombre: String = "",
        val partidas: Int = 0,
        val listajugadores: List<JugadorEntity> = emptyList()
    )

    fun JugadorUiState.toJugadorEntity() = JugadorEntity(
        jugadorId = jugadorId,
        nombre = nombre,
        partidas = partidas
    )

    fun onEvent(event: JugadorUiEvent){
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
                    repository.save(_uiState.value.toJugadorEntity())
                }
            }
        }
    }
}