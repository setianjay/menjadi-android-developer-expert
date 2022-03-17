package com.setianjay.watchme.core.data

import com.setianjay.watchme.core.data.source.remote.vo.ApiResponse
import kotlinx.coroutines.flow.*

/**
 * class for handle offline-online mode
 * */
abstract class NetworkBoundResource<RESULT_TYPE, REQUEST_TYPE> {

    private var result: Flow<Resource<RESULT_TYPE>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDb().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResource = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResource.data)
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResource.message))
                }
            }
        } else {
            emitAll(loadFromDb().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDb(): Flow<RESULT_TYPE>

    protected abstract fun shouldFetch(data: RESULT_TYPE?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<REQUEST_TYPE>>

    protected abstract suspend fun saveCallResult(data: REQUEST_TYPE)

    fun asFlow(): Flow<Resource<RESULT_TYPE>> = result
}