package com.setianjay.watchme.core.data

import com.setianjay.watchme.core.constants.RemoteConst
import com.setianjay.watchme.core.data.source.local.LocalMovieDataSource
import com.setianjay.watchme.core.data.source.remote.RemoteMovieDataSource
import com.setianjay.watchme.core.data.source.remote.model.MoviesResponse.MoviesItem
import com.setianjay.watchme.core.data.source.remote.model.TvResponse.TvShowItem
import com.setianjay.watchme.core.data.source.remote.vo.ApiResponse
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.repository.MovieRepository
import com.setianjay.watchme.core.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val localMovieDataSource: LocalMovieDataSource,
    private val remoteMovieDataSource: RemoteMovieDataSource
) : MovieRepository {

    override fun checkMovieIsFavorite(movieId: Long): Flow<Movie?> {
        return localMovieDataSource.getMovieFavorite(movieId).map {
            it?.toMovie()
        }
    }

    override fun getMoviesFavorite(): Flow<List<Movie>> {
        return localMovieDataSource.getAllMovieFavorite(true).map {
            it.movieFavoriteEntityToMovie()
        }
    }

    override fun getMoviesNowPlaying(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MoviesItem>>() {
            override fun loadFromDb(): Flow<List<Movie>> {
                return localMovieDataSource.getAllMovieNowPlaying(true).map {
                    it.movieNowPlayingEntityToMovie()
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesItem>>> {
                return remoteMovieDataSource.getMovies(RemoteConst.NOW_PLAYING)
            }

            override suspend fun saveCallResult(data: List<MoviesItem>) {
                val movieNowPlayingEntity = data.movieItemToMovieNowPlayingEntity()
                localMovieDataSource.insertMovieNowPlaying(movieNowPlayingEntity)
            }

        }.asFlow()
    }

    override fun getMoviesPopular(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MoviesItem>>() {
            override fun loadFromDb(): Flow<List<Movie>> {
                return localMovieDataSource.getAllMoviePopular(true).map {
                    it.moviePopularEntityToMovie()
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesItem>>> {
                return remoteMovieDataSource.getMovies(RemoteConst.POPULAR)
            }

            override suspend fun saveCallResult(data: List<MoviesItem>) {
                val moviePopularEntity = data.movieItemToMoviePopularEntity()
                localMovieDataSource.insertMoviePopular(moviePopularEntity)
            }

        }.asFlow()
    }

    override fun getTvFavorite(): Flow<List<Movie>> {
        return localMovieDataSource.getAllMovieFavorite(false).map {
            it.movieFavoriteEntityToMovie()
        }
    }

    override fun getTvNowPlaying(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<TvShowItem>>() {
            override fun loadFromDb(): Flow<List<Movie>> {
                return localMovieDataSource.getAllMovieNowPlaying(false).map {
                    it.movieNowPlayingEntityToMovie()
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowItem>>> {
                return remoteMovieDataSource.getTvs(RemoteConst.ON_THE_AIR)
            }

            override suspend fun saveCallResult(data: List<TvShowItem>) {
                val tvNowPlayingEntity = data.tvShowItemToMovieNowPlayingEntity()
                localMovieDataSource.insertMovieNowPlaying(tvNowPlayingEntity)
            }
        }.asFlow()
    }

    override fun getTvPopular(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<TvShowItem>>() {
            override fun loadFromDb(): Flow<List<Movie>> {
                return localMovieDataSource.getAllMoviePopular(false).map {
                    it.moviePopularEntityToMovie()
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowItem>>> {
                return remoteMovieDataSource.getTvs(RemoteConst.POPULAR)
            }

            override suspend fun saveCallResult(data: List<TvShowItem>) {
                val moviePopularEntity = data.tvShowItemToMoviePopularEntity()
                localMovieDataSource.insertMoviePopular(moviePopularEntity)
            }

        }.asFlow()
    }

    override suspend fun searchMoviesByTitle(title: String): Resource<List<Movie>> {
        return when (val response = remoteMovieDataSource.searchMovies(title)) {
            is ApiResponse.Success -> {
                Resource.Success(response.data.moviesItemToMovie())
            }
            is ApiResponse.Empty -> {
                Resource.Error(RemoteConst.ERR_CODE_EMPTY)
            }
            is ApiResponse.Error -> {
                Resource.Error(response.errorCode)
            }
        }
    }

    override suspend fun searchTvByTitle(title: String): Resource<List<Movie>> {
        return when (val response = remoteMovieDataSource.searchTv(title)) {
            is ApiResponse.Success -> {
                Resource.Success(response.data.tvShowItemToMovie())
            }
            is ApiResponse.Empty -> {
                Resource.Error(RemoteConst.ERR_CODE_EMPTY)
            }
            is ApiResponse.Error -> {
                Resource.Error(response.errorCode)
            }
        }
    }

    override fun setMovieFavorite(movie: Movie) {
        val movieFavoriteEntity = movie.toFavoriteEntity()
        CoroutineScope(Dispatchers.IO).launch {
            localMovieDataSource.insertMovieFavorite(
                movieFavoriteEntity
            )
        }
    }

    override fun unsetMovieFavorite(movie: Movie) {
        val movieFavoriteEntity = movie.toFavoriteEntity()
        CoroutineScope(Dispatchers.IO).launch {
            localMovieDataSource.deleteMovieFavorite(
                movieFavoriteEntity
            )
        }
    }
}