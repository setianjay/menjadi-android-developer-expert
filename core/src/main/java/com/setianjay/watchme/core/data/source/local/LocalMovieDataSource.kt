package com.setianjay.watchme.core.data.source.local

import com.setianjay.watchme.core.data.source.local.persistence.model.MovieNowPlayingEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MoviePopularEntity
import kotlinx.coroutines.flow.Flow

interface LocalMovieDataSource {
    fun getAllMoviesPopular(): Flow<List<MoviePopularEntity>>

    fun getAllTvPopular(): Flow<List<MoviePopularEntity>>

    fun getAllMoviesNowPlaying(): Flow<List<MovieNowPlayingEntity>>

    fun getAllTvNowPlaying(): Flow<List<MovieNowPlayingEntity>>

    suspend fun insertMoviePopular(movies: List<MoviePopularEntity>)

    suspend fun insertMovieNowPlaying(movies: List<MovieNowPlayingEntity>)
}