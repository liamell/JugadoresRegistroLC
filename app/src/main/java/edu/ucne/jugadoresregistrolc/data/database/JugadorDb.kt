package edu.ucne.jugadoresregistrolc.data.database;

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.jugadoresregistrolc.data.dao.JugadorDao
import edu.ucne.jugadoresregistrolc.data.entities.JugadorEntity

@Database(
    entities = [
        JugadorEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class JugadorDb : RoomDatabase() {
    abstract fun jugadorDao(): JugadorDao
}
