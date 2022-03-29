package com.setianjay.watchme.core.domain.repository

import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun checkMovieIsFavorite(movieId: Long): Flow<Movie?>

    fun getMoviesFavorite(): Flow<List<Movie>>

    fun getMoviesNowPlaying(): Flow<Resource<List<Movie>>>

    fun getMoviesPopular(): Flow<Resource<List<Movie>>>

    fun getTvFavorite(): Flow<List<Movie>>

    fun getTvNowPlaying(): Flow<Resource<List<Movie>>>

    fun getTvPopular(): Flow<Resource<List<Movie>>>

    fun searchMoviesByTitle(title: String): Flow<Resource<List<Movie>>>

    fun searchTvByTitle(title: String): Flow<Resource<List<Movie>>>

    fun setMovieFavorite(movie: Movie)

    fun unsetMovieFavorite(movie: Movie)
}