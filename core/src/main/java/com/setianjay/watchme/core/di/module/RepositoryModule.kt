package com.setianjay.watchme.core.di.module

import com.setianjay.watchme.core.data.MovieRepositoryImpl
import com.setianjay.watchme.core.data.source.local.LocalMovieDataSource
import com.setianjay.watchme.core.data.source.local.LocalMovieDataSourceImpl
import com.setianjay.watchme.core.data.source.remote.RemoteMovieDataSource
import com.setianjay.watchme.core.data.source.remote.RemoteMovieDataSourceImpl
import com.setianjay.watchme.core.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideLocalMovieDataSource(movieLocalMovieDataSourceImpl: LocalMovieDataSourceImpl): LocalMovieDataSource

    @Binds
    abstract fun provideRemoteMovieDataSource(remoteMovieDataSourceImpl: RemoteMovieDataSourceImpl): RemoteMovieDataSource

    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}