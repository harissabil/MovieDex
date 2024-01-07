package com.harissabil.moviedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harissabil.moviedex.core.database.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}