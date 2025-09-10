package edu.ucne.jugadoresregistrolc.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity
import kotlinx.coroutines.flow.Flow

@Dao
public interface JugadorDao {
    @Upsert()
    suspend fun save(jugador: JugadorEntity)
    @Query(
        """SELECT * FROM Jugadores WHERE jugadorId == :id limit 1"""
    )
    suspend fun find(id: Int): JugadorEntity?
    @Delete
    suspend fun delete(jugador: JugadorEntity)
    @Query("SELECT * FROM Jugadores")
    fun getAll(): Flow<List<JugadorEntity>>
}