package com.setianjay.watchme.di.module

import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * this dependency provides what dependency injection requires in the favorite module
 * */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun movieUseCase(): MovieUseCase
}