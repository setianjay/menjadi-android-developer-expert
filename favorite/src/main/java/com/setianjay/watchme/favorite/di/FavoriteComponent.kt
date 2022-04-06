package com.setianjay.watchme.favorite.di

import android.content.Context
import com.setianjay.watchme.di.module.FavoriteModuleDependencies
import com.setianjay.watchme.favorite.presentation.content.FavoriteMovieFragment
import dagger.BindsInstance
import dagger.Component


@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteMovieFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}