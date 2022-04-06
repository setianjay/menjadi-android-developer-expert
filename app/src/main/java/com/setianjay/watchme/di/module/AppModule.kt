package com.setianjay.watchme.di.module

import com.setianjay.watchme.core.domain.usecase.MovieUseCase
import com.setianjay.watchme.core.domain.usecase.MovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieUseCaseImpl: MovieUseCaseImpl): MovieUseCase
}