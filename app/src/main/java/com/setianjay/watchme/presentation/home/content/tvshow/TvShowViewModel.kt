package com.setianjay.watchme.presentation.home.content.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getTvNowPlaying(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getTvNowPlaying().asLiveData()
    }

    fun getTvPopular(): LiveData<Resource<List<Movie>>>{
        return movieUseCase.getTvPopular().asLiveData()
    }
}