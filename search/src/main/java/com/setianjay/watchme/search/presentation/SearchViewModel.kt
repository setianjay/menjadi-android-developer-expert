package com.setianjay.watchme.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@FlowPreview
class SearchViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)


    fun getMovie(type: Int): LiveData<Resource<List<Movie>>> =
        queryChannel.asFlow()
            .debounce(300L)
            .distinctUntilChanged()
            .filter { title ->
                title.trim().isNotEmpty()
            }
            .mapLatest { title ->
                if (type == 0) {
                    movieUseCase.searchMoviesByTitle(title)
                } else {
                    movieUseCase.searchTvByTitle(title)
                }
            }
            .asLiveData()
}