package com.setianjay.watchme.presentation.home.content.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getMoviesNowPlaying(): LiveData<Resource<List<Movie>>>{
        return movieUseCase.getMoviesNowPlaying().asLiveData()
    }

    fun getMoviesPopular(): LiveData<Resource<List<Movie>>>{
        return movieUseCase.getMoviesPopular().asLiveData()
    }
}