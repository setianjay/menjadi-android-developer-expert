package com.setianjay.watchme.core.domain.usecase

import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository): MovieUseCase {
    override fun checkMovieIsFavorite(movieId: Long): Flow<Movie?> {
        return movieRepository.checkMovieIsFavorite(movieId)
    }

    override fun getMoviesFavorite(): Flow<List<Movie>> {
        return movieRepository.getMoviesFavorite()
    }

    override fun getMoviesNowPlaying(): Flow<Resource<List<Movie>>> {
        return movieRepository.getMoviesNowPlaying()
    }

    override fun getMoviesPopular(): Flow<Resource<List<Movie>>> {
        return movieRepository.getMoviesPopular()
    }

    override fun getTvFavorite(): Flow<List<Movie>> {
        return movieRepository.getTvFavorite()
    }

    override fun getTvNowPlaying(): Flow<Resource<List<Movie>>> {
        return movieRepository.getTvNowPlaying()
    }

    override fun getTvPopular(): Flow<Resource<List<Movie>>> {
        return movieRepository.getTvPopular()
    }

    override fun searchMoviesByTitle(title: String): Flow<Resource<List<Movie>>> {
        return movieRepository.searchMoviesByTitle(title)
    }

    override fun searchTvByTitle(title: String): Flow<Resource<List<Movie>>> {
        return movieRepository.searchTvByTitle(title)
    }

    override fun setMovieFavorite(movie: Movie) {
        movieRepository.setMovieFavorite(movie)
    }

    override fun unsetMovieFavorite(movie: Movie) {
        movieRepository.unsetMovieFavorite(movie)
    }
}