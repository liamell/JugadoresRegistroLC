package edu.ucne.jugadoresregistrolc.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.jugadoresregistrolc.data.database.JugadorDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun ProvidesJugadorDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            JugadorDb::class.java,
            "Jugador.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun ProvidesJugadorDao(jugadorDb: JugadorDb) = jugadorDb.jugadorDao()

    @Provides
    @Singleton
    fun ProvidesPartidaDao(jugadorDb: JugadorDb) = jugadorDb.partidaDao()

}
