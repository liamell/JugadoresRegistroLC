package edu.ucne.jugadoresregistrolc.data.repositories;

import edu.ucne.jugadoresregistrolc.data.dao.JugadorDao
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity
import javax.inject.Inject

class JugadorRepository @Inject constructor(
    private val jugadorDao: JugadorDao
)
{
    suspend fun save(jugador: JugadorEntity) = jugadorDao.save(jugador)
    suspend fun find(id: Int) = jugadorDao.find(id)
    suspend fun delete(jugador: JugadorEntity) = jugadorDao.delete(jugador)
    fun getAll() = jugadorDao.getAll()
}