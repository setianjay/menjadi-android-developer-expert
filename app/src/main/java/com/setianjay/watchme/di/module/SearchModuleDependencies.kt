package com.setianjay.watchme.di.module

import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SearchModuleDependencies {

    fun provideMovieUseCase(): MovieUseCase
}