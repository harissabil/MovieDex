package com.harissabil.moviedex.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harissabil.moviedex.core.database.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie WHERE movie_id = :movieId")
    suspend fun isFavorite(movieId: Int): MovieEntity

    @Query("SELECT * FROM movie ORDER BY id DESC")
    fun getAllMovies(): Flow<List<MovieEntity>>
}