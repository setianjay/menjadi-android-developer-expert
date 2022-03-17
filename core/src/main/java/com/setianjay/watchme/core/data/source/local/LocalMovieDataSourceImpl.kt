package com.setianjay.watchme.core.data.source.local

import com.setianjay.watchme.core.data.source.local.persistence.model.MovieNowPlayingEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MoviePopularEntity
import com.setianjay.watchme.core.data.source.local.persistence.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMovieDataSourceImpl @Inject constructor(private val movieDao: MovieDao): LocalMovieDataSource {
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

    override suspend fun insertMovieNowPlaying(movies: List<MovieNowPlayingEntity>) {
        movieDao.insertMovieNowPlaying(movies)
    }

    override suspend fun insertMoviePopular(movies: List<MoviePopularEntity>) {
        movieDao.insertMoviePopular(movies)
    }
}