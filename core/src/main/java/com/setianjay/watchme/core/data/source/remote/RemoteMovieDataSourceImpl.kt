package com.setianjay.watchme.core.data.source.remote

import com.setianjay.watchme.core.constants.RemoteConst
import com.setianjay.watchme.core.data.source.remote.model.MoviesResponse.MoviesItem
import com.setianjay.watchme.core.data.source.remote.model.TvResponse.TvShowItem
import com.setianjay.watchme.core.data.source.remote.network.MovieDbEndPoint
import com.setianjay.watchme.core.data.source.remote.vo.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteMovieDataSourceImpl @Inject constructor(private val movieDbEndPoint: MovieDbEndPoint): RemoteMovieDataSource {

    override suspend fun getMovies(movieType: String): Flow<ApiResponse<List<MoviesItem>>> {
        return flow{
            try {
                val response = movieDbEndPoint.getMovies(movieType)
                val listMovie = response.moviesItems

                if (listMovie.isNotEmpty()){
                    emit(ApiResponse.Success(listMovie))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(RemoteConst.ERR_CODE_API))
                Timber.e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTvs(tvType: String): Flow<ApiResponse<List<TvShowItem>>> {
        return flow{
            try {
                val response = movieDbEndPoint.getTvs(tvType)
                val listTvShow = response.tvShowItems

                if (listTvShow.isNotEmpty()){
                    emit(ApiResponse.Success(listTvShow))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(RemoteConst.ERR_CODE_API))
                Timber.e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun searchMovies(query: String): ApiResponse<List<MoviesItem>> {
        return try{
            val response = movieDbEndPoint.searchMovies(query)
            val listMovies = response.moviesItems

            if (listMovies.isNotEmpty()){
                ApiResponse.Success(listMovies)
            }else{
                ApiResponse.Empty
            }
        }catch (e: Exception){
            Timber.e(e.toString())
            ApiResponse.Error(RemoteConst.ERR_CODE_API)
        }
    }

    override suspend fun searchTv(query: String): ApiResponse<List<TvShowItem>> {
        return try{
            val response = movieDbEndPoint.searchTv(query)
            val listMovies = response.tvShowItems

            if (listMovies.isNotEmpty()){
                ApiResponse.Success(listMovies)
            }else{
                ApiResponse.Empty
            }
        }catch (e: Exception){
            Timber.e(e.toString())
            ApiResponse.Error(RemoteConst.ERR_CODE_API)
        }
    }
}