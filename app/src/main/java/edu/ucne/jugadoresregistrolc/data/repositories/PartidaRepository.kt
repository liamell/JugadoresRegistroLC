package edu.ucne.jugadoresregistrolc.data.repositories
import edu.ucne.jugadoresregistrolc.data.dao.PartidaDao
import edu.ucne.jugadoresregistrolc.data.entities.PartidaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PartidaRepository @Inject constructor(
    private val partidaDao: PartidaDao
) {
    fun getAll(): Flow<List<PartidaEntity>> = partidaDao.getAll()

    suspend fun getById(id: Int): PartidaEntity? = partidaDao.getById(id)

    suspend fun insert(partida: PartidaEntity) = partidaDao.insert(partida)

    suspend fun update(partida: PartidaEntity) = partidaDao.update(partida)

    suspend fun delete(partida: PartidaEntity) = partidaDao.delete(partida)

}