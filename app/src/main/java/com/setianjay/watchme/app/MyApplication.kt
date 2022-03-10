package com.setianjay.watchme.app

import android.app.Application
import com.setianjay.watchme.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber(){
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}