package com.setianjay.watchme.favorite.presentation.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getMoviesFavorite(): LiveData<List<Movie>>{
        return movieUseCase.getMoviesFavorite().asLiveData()
    }

    fun getTvFavorite(): LiveData<List<Movie>>{
        return movieUseCase.getTvFavorite().asLiveData()
    }
}