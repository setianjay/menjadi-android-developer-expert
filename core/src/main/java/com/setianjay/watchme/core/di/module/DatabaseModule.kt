package com.setianjay.watchme.core.di.module

import android.content.Context
import androidx.room.Room
import com.setianjay.watchme.core.data.source.local.persistence.room.MovieDao
import com.setianjay.watchme.core.data.source.local.persistence.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase{
        return Room.databaseBuilder(
            context,
        MovieDatabase::class.java,
        "movie.db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()
}