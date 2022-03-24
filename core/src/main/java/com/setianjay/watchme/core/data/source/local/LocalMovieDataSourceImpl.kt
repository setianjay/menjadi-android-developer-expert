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

    override fun getAllMoviesNowPlaying(): Flow<List<MovieNowPlayingEntity>> {
        return movieDao.getAllMoviesNowPlaying()
    }

    override fun getAllTvNowPlaying(): Flow<List<MovieNowPlayingEntity>> {
        return movieDao.getAllTvNowPlaying()
    }

    override fun getAllMoviesPopular(): Flow<List<MoviePopularEntity>> {
        return movieDao.getAllMoviesPopular()
    }

    override fun getAllTvPopular(): Flow<List<MoviePopularEntity>> {
        return movieDao.getAllTvPopular()
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