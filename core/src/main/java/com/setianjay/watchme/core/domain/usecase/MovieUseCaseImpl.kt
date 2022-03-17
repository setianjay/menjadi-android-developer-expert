package com.setianjay.watchme.core.domain.usecase

import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository): MovieUseCase {
    override fun getMoviesNowPlaying(): Flow<Resource<List<Movie>>> {
        return movieRepository.getMoviesNowPlaying()
    }

    override fun getMoviesPopular(): Flow<Resource<List<Movie>>> {
        return movieRepository.getMoviesPopular()
    }

    override fun getTvNowPlaying(): Flow<Resource<List<Movie>>> {
        return movieRepository.getTvNowPlaying()
    }

    override fun getTvPopular(): Flow<Resource<List<Movie>>> {
        return movieRepository.getTvPopular()
    }
}