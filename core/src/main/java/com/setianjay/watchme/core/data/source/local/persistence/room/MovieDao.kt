package com.setianjay.watchme.core.data.source.local.persistence.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.setianjay.watchme.core.data.source.local.persistence.model.MovieNowPlayingEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MoviePopularEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    /**
     * get all movies now playing in tbl_movie_now_playing
     *
     * note : 1 is true, true means for getting data movies
     * */
    @Query("SELECT * FROM tbl_movie_now_playing WHERE is_movies = 1")
    fun getAllMoviesNowPlaying(): Flow<List<MovieNowPlayingEntity>>

    /**
     * get all tv show now playing in tbl_movie_now_playing
     *
     * note : 0 is false, false means for getting data tv show
     * */
    @Query("SELECT * FROM tbl_movie_now_playing WHERE is_movies = 0")
    fun getAllTvNowPlaying(): Flow<List<MovieNowPlayingEntity>>

    /**
     * get all movies popular in tbl_movie_popular
     *
     * note : 1 is true, true means for getting data movies
     * */
    @Query("SELECT * FROM tbl_movie_popular WHERE is_movies = 1")
    fun getAllMoviesPopular(): Flow<List<MoviePopularEntity>>

    /**
     * get all tv show popular in tbl_movie_popular
     *
     * note : 0 is false, false means for getting data tv show
     * */
    @Query("SELECT * FROM tbl_movie_popular WHERE is_movies = 0")
    fun getAllTvPopular(): Flow<List<MoviePopularEntity>>

    /**
     * insert data to tbl_movie_now_playing
     *
     * @param listMovie     list value/field from tbl_movie_now_playing
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieNowPlaying(listMovie: List<MovieNowPlayingEntity>)

    /**
     * insert data to tbl_movie_popular
     *
     * @param listMovie     list value/field from tbl_movie_popular
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviePopular(listMovie: List<MoviePopularEntity>)
}