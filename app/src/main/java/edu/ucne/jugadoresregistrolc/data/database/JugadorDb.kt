package edu.ucne.jugadoresregistrolc.data.database;

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.jugadoresregistrolc.data.dao.JugadorDao
import edu.ucne.jugadoresregistrolc.data.dao.PartidaDao
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity
import edu.ucne.jugadoresregistrolc.data.entities.PartidaEntity

@Database(
    entities = [
        JugadorEntity::class,
        PartidaEntity::class

    ],
    version = 2,
    exportSchema = false
)

abstract class JugadorDb : RoomDatabase() {
    abstract fun jugadorDao(): JugadorDao
    abstract fun partidaDao(): PartidaDao
}
