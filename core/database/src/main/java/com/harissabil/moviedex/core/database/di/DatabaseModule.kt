package com.harissabil.moviedex.core.database.di

import android.content.Context
import androidx.room.Room
import com.harissabil.moviedex.core.database.LocalDataSource
import com.harissabil.moviedex.core.database.MovieDao
import com.harissabil.moviedex.core.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private val passphrase: ByteArray = SQLiteDatabase.getBytes("harissabil".toCharArray())
    private val factory = SupportFactory(passphrase)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context = context,
        klass = MovieDatabase::class.java,
        name = "Movie.db"
    ).fallbackToDestructiveMigration().openHelperFactory(factory).build()

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()

    @Provides
    fun provideLocalDataSource(movieDao: MovieDao): LocalDataSource {
        return LocalDataSource(movieDao)
    }
}