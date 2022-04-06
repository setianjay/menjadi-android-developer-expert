package com.setianjay.watchme.search.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import com.setianjay.watchme.search.presentation.SearchViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(movieUseCase) as T
            }
            else -> throw Throwable("Unknown view model class ${modelClass.name}")
        }
    }
}