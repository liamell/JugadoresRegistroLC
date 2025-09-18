package edu.ucne.jugadoresregistrolc.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.jugadoresregistrolc.data.entities.PartidaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PartidaDao {
    @Query("SELECT * FROM partidas")
    fun getAll(): Flow<List<PartidaEntity>>

    @Insert
    suspend fun insert(partida: PartidaEntity)

    @Update
    suspend fun update(partida: PartidaEntity)

    @Delete
    suspend fun delete(partida: PartidaEntity)


    @Query("SELECT * FROM partidas WHERE partidaId = :id")
    suspend fun getById(id: Int): PartidaEntity?
}
