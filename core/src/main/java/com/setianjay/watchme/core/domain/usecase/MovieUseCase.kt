package com.setianjay.watchme.core.domain.usecase

import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMoviesNowPlaying(): Flow<Resource<List<Movie>>>

    fun getMoviesPopular(): Flow<Resource<List<Movie>>>

    fun getTvNowPlaying(): Flow<Resource<List<Movie>>>

    fun getTvPopular(): Flow<Resource<List<Movie>>>
}