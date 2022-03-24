package com.setianjay.watchme.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import com.setianjay.watchme.favorite.presentation.content.FavoriteMovieViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(private val movieUseCase: MovieUseCase): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieUseCase) as T
            }
            else -> {
                throw Throwable("Unknown view model class ${modelClass.name}")
            }
        }
    }
}