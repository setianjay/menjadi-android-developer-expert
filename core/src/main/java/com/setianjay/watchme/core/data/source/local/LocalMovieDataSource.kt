package com.setianjay.watchme.core.data.source.local

import com.setianjay.watchme.core.data.source.local.persistence.model.MovieFavoriteEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MovieNowPlayingEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MoviePopularEntity
import kotlinx.coroutines.flow.Flow

interface LocalMovieDataSource {
    fun getMovieFavorite(movieId: Long): Flow<MovieFavoriteEntity?>

    fun deleteMovieFavorite(movie: MovieFavoriteEntity)

    fun getAllMoviesNowPlaying(): Flow<List<MovieNowPlayingEntity>>

    fun getAllTvNowPlaying(): Flow<List<MovieNowPlayingEntity>>

    fun getAllMoviesPopular(): Flow<List<MoviePopularEntity>>

    fun getAllTvPopular(): Flow<List<MoviePopularEntity>>

    fun insertMovieFavorite(movie: MovieFavoriteEntity)

    suspend fun insertMovieNowPlaying(movie: List<MovieNowPlayingEntity>)

    suspend fun insertMoviePopular(movie: List<MoviePopularEntity>)
}