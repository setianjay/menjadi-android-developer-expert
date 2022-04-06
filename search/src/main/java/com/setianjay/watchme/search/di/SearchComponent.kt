package com.setianjay.watchme.search.di

import android.content.Context
import com.setianjay.watchme.di.module.SearchModuleDependencies
import com.setianjay.watchme.search.presentation.SearchActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [SearchModuleDependencies::class])
interface SearchComponent {

    fun inject(fragment: SearchActivity)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(searchModuleDependencies: SearchModuleDependencies): Builder
        fun build(): SearchComponent
    }
}