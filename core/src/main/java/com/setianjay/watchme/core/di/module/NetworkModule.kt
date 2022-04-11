package com.setianjay.watchme.core.di.module

import com.setianjay.watchme.core.BuildConfig
import com.setianjay.watchme.core.BuildConfig.CERTIFICATES_PINNER as CERTIFICATES
import com.setianjay.watchme.core.constants.RemoteConst
import com.setianjay.watchme.core.data.source.remote.network.MovieDbEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG){
                HttpLoggingInterceptor.Level.BODY
            }else{
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient{
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, *CERTIFICATES)
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    fun provideMovieDbServices(client: OkHttpClient): MovieDbEndPoint{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RemoteConst.BASE_URL)
            .client(client)
            .build()

        return retrofit.create(MovieDbEndPoint::class.java)
    }

    companion object{
        private const val TIMEOUT: Long = 120L
    }
}