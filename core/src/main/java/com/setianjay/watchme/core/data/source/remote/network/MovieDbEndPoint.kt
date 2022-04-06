package com.setianjay.watchme.core.data.source.remote.network

import com.setianjay.watchme.core.data.source.remote.model.MoviesResponse
import com.setianjay.watchme.core.data.source.remote.model.TvResponse
import com.setianjay.watchme.core.BuildConfig.API_KEY as API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbEndPoint {

    /**
     * getting movies based on movies type
     *
     * @param movieType(mandatory)      type of movies. value of type has provided in ApiConst (popular or now_playing)
     * @param apiKey(not mandatory)     api key. value of api key has default value
     * */
    @GET("movie/{movie_type}")
    suspend fun getMovies(@Path("movie_type") movieType: String, @Query("api_key") apiKey: String = API_KEY): MoviesResponse


    /**
     * getting tv based on tv type
     *
     * @param tvType(mandatory)         type of tv. value of type has provided in ApiConst (popular or on_the_air)
     * @param apiKey(not mandatory)     api key. value of api key has default value
     * */
    @GET("tv/{tv_type}")
    suspend fun getTvs(@Path("tv_type") tvType: String, @Query("api_key") apiKey: String = API_KEY): TvResponse

    /**
     * search data movie based on query
     *
     * @param query                     query means movie title
     * @param apiKey                    api key. value of api key has default value
     * */
    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String, @Query("api_key") apiKey: String = API_KEY): MoviesResponse

    /**
     * search data tv based on query
     *
     * @param query                     query means tv title
     * @param apiKey                    api key. value of api key has default value
     * */
    @GET("search/tv")
    suspend fun searchTv(@Query("query") query: String, @Query("api_key") apiKey: String = API_KEY): TvResponse
}