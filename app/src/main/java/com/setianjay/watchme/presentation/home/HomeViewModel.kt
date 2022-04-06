package com.setianjay.watchme.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getMovieSlider(): LiveData<Resource<List<Movie>>>{
        return movieUseCase.getMoviesNowPlaying().asLiveData()
    }

}