package com.setianjay.watchme.core.data.source.local

import com.setianjay.watchme.core.data.source.local.persistence.model.MovieFavoriteEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MovieNowPlayingEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MoviePopularEntity
import com.setianjay.watchme.core.data.source.local.persistence.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMovieDataSourceImpl @Inject constructor(private val movieDao: MovieDao): LocalMovieDataSource {

    override fun getMovieFavorite(movieId: Long): Flow<MovieFavoriteEntity?> {
        return movieDao.getMovieFavorite(movieId)
    }

    override fun deleteMovieFavorite(movie: MovieFavoriteEntity) {
        movieDao.deleteMovieFavorite(movie)
    }

    override fun getAllMovieFavorite(isMovies: Boolean): Flow<List<MovieFavoriteEntity>> {
        return movieDao.getAllMovieFavorite(isMovies)
    }

    override fun getAllMovieNowPlaying(isMovies: Boolean): Flow<List<MovieNowPlayingEntity>> {
        return movieDao.getAllMovieNowPlaying(isMovies)
    }

    override fun getAllMoviePopular(isMovies: Boolean): Flow<List<MoviePopularEntity>> {
        return movieDao.getAllMoviePopular(isMovies)
    }

    override fun insertMovieFavorite(movie: MovieFavoriteEntity) {
        movieDao.insertMovieFavorite(movie)
    }

    override suspend fun insertMovieNowPlaying(movie: List<MovieNowPlayingEntity>) {
        movieDao.insertMovieNowPlaying(movie)
    }

    override suspend fun insertMoviePopular(movie: List<MoviePopularEntity>) {
        movieDao.insertMoviePopular(movie)
    }
}