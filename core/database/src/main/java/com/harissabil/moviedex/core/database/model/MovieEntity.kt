package com.harissabil.moviedex.core.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String
) : Parcelable
