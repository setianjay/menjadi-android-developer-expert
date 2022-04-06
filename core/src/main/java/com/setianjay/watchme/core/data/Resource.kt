package com.setianjay.watchme.core.data

sealed class Resource<T>(val data: T?, val errorCode: Int?) {
    class Loading<T>: Resource<T>(null, null)
    class Success<T>(data: T): Resource<T>(data, null)
    class Error<T>(errorCode: Int): Resource<T>(null, errorCode)
}