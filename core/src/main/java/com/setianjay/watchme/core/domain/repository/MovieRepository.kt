package com.setianjay.watchme.core.domain.repository

import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.data.source.local.persistence.model.MovieFavoriteEntity
import com.setianjay.watchme.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun checkMovieIsFavorite(movieId: Long): Flow<Movie?>

    fun getMoviesNowPlaying(): Flow<Resource<List<Movie>>>

    fun getMoviesPopular(): Flow<Resource<List<Movie>>>

    fun getTvNowPlaying(): Flow<Resource<List<Movie>>>

    fun getTvPopular(): Flow<Resource<List<Movie>>>

    fun setMovieFavorite(movie: Movie)

    fun unsetMovieFavorite(movie: Movie)
}