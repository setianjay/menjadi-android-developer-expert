package com.setianjay.watchme.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    fun checkMovieIsFavorite(movieId: Long): LiveData<Movie?>{
        return movieUseCase.checkMovieIsFavorite(movieId).asLiveData()
    }

    fun setMovieFavorite(movie: Movie){
        movieUseCase.setMovieFavorite(movie)
    }

    fun unsetMovieFavorite(movie: Movie){
        movieUseCase.unsetMovieFavorite(movie)
    }
}