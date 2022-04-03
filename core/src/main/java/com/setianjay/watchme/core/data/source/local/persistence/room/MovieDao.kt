package com.setianjay.watchme.core.data.source.local.persistence.room

import androidx.room.*
import com.setianjay.watchme.core.data.source.local.persistence.model.MovieFavoriteEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MovieNowPlayingEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MoviePopularEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    /**
     * get all movie now playing in tbl_movie_now_playing
     *
     * @param isMovies      true for movies now playing, otherwise is tv now playing
     * @return              list movie now playing
     * */
    @Query("SELECT * FROM tbl_movie_now_playing WHERE is_movies = :isMovies")
    fun getAllMovieNowPlaying(isMovies: Boolean): Flow<List<MovieNowPlayingEntity>>

    /**
     * get all movie popular in tbl_movie_popular
     *
     * @param isMovies      true for movies popular, otherwise is tv popular
     * @return              list movie popular
     * */
    @Query("SELECT * FROM tbl_movie_popular WHERE is_movies = :isMovies")
    fun getAllMoviePopular(isMovies: Boolean): Flow<List<MoviePopularEntity>>

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

    /**
     * delete movie favorite from tbl_movie_favorite
     *
     * @param movieFavoriteEntity   local database model for tbl_movie_favorite
     * */
    @Delete
    fun deleteMovieFavorite(movieFavoriteEntity: MovieFavoriteEntity)

    /**
     * get all movie favorite based on is_movies
     *
     * @param isMovies      true for movie type is movies. otherwise movie type is tv
     * */
    @Query("SELECT * FROM tbl_movie_favorite WHERE is_movies = :isMovies")
    fun getAllMovieFavorite(isMovies: Boolean): Flow<List<MovieFavoriteEntity>>

    /**
     * get specific movie favorite based on movie id
     *
     * @param movieId       id of movie
     * */
    @Query("SELECT * FROM tbl_movie_favorite WHERE movie_id = :movieId")
    fun getMovieFavorite(movieId: Long): Flow<MovieFavoriteEntity?>

    /**
     * insert data to tbl_movie_favorite
     *
     * @param movieFavoriteEntity   local database model for tbl_movie_favorite
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieFavorite(movieFavoriteEntity: MovieFavoriteEntity)



}