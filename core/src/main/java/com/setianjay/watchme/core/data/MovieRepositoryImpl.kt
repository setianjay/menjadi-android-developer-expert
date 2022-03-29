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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val localMovieDataSource: LocalMovieDataSource,
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val executors: AppExecutor
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
                return localMovieDataSource.getAllMoviesNowPlaying().map {
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
                return localMovieDataSource.getAllMoviesPopular().map {
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
                return localMovieDataSource.getAllTvNowPlaying().map {
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
                return localMovieDataSource.getAllTvPopular().map {
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

    override fun searchMoviesByTitle(title: String): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            emitAll(remoteMovieDataSource.searchMovies(title).map { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        Resource.Success(response.data.moviesItemToMovie())
                    }
                    is ApiResponse.Error -> {
                        Resource.Error(response.errorCode)
                    }
                    else -> {
                        Resource.Error(RemoteConst.ERR_CODE_EMPTY)
                    }
                }
            })
        }

    }

    override fun searchTvByTitle(title: String): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            emitAll(remoteMovieDataSource.searchTv(title).map { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        Resource.Success(response.data.tvShowItemToMovie())
                    }
                    is ApiResponse.Error -> {
                        Resource.Error(response.errorCode)
                    }
                    else -> {
                        Resource.Error(RemoteConst.ERR_CODE_EMPTY)
                    }
                }
            })
        }
    }

    override fun setMovieFavorite(movie: Movie) {
        val movieFavoriteEntity = movie.toFavoriteEntity()
        executors.singleThread()
            .execute { localMovieDataSource.insertMovieFavorite(movieFavoriteEntity) }
    }

    override fun unsetMovieFavorite(movie: Movie) {
        val movieFavoriteEntity = movie.toFavoriteEntity()
        executors.singleThread()
            .execute { localMovieDataSource.deleteMovieFavorite(movieFavoriteEntity) }
    }
}