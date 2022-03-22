package com.setianjay.watchme.core.data.source.local.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.setianjay.watchme.core.data.source.local.persistence.model.MovieFavoriteEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MovieNowPlayingEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MoviePopularEntity

/**
 * class as representing local database
 * */
@Database(
    entities = [MovieNowPlayingEntity::class, MoviePopularEntity::class, MovieFavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}