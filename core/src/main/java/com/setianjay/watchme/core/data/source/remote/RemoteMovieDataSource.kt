package com.setianjay.watchme.core.data.source.remote

import com.setianjay.watchme.core.data.source.remote.model.MoviesResponse.MoviesItem
import com.setianjay.watchme.core.data.source.remote.model.TvResponse.TvShowItem
import com.setianjay.watchme.core.data.source.remote.vo.ApiResponse
import kotlinx.coroutines.flow.Flow

interface RemoteMovieDataSource {

    suspend fun getMovies(movieType: String): Flow<ApiResponse<List<MoviesItem>>>

    suspend fun getTvs(tvType: String): Flow<ApiResponse<List<TvShowItem>>>
}