package com.setianjay.watchme.search.presentation

import androidx.lifecycle.*
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val searchMoviesResult: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    fun getMovie(): LiveData<Resource<List<Movie>>>{
        return searchMoviesResult
    }

    fun searchMoviesByTitle(title: String) {
        viewModelScope.launch {
            movieUseCase.searchMoviesByTitle(title).collect {
                searchMoviesResult.value = it
            }
        }
    }

    fun searchTvByTitle(title: String) {
        viewModelScope.launch {
            movieUseCase.searchTvByTitle(title).collect {
                searchMoviesResult.value = it
            }
        }
    }
}