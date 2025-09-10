package edu.ucne.jugadoresregistrolc.data.entities;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jugadores")
data public class JugadorEntity(
    @PrimaryKey
    val jugadorId: Int? = null,
    val nombre: String,
    val partidas: Int
)