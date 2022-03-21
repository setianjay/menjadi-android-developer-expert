package com.setianjay.watchme.core.data.source.local.persistence.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * local database model representing as table for holding data about now playing,
 * like movie now playing and tv show now playing.
 * */
@Entity(tableName = "tbl_movie_now_playing")
data class MovieNowPlayingEntity (
    @ColumnInfo(name = "movie_backdrop")
    val movieBackdrop: String?,

    @ColumnInfo(name = "movie_genres")
    val movieGenre: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    val movieId: Long,

    @ColumnInfo(name = "movie_language")
    val movieLanguage: String,

    @ColumnInfo(name = "movie_overview")
    val movieOverview: String,

    @ColumnInfo(name = "movie_popularity")
    val moviePopularity: Double,

    @ColumnInfo(name = "movie_poster")
    @NonNull
    val moviePoster: String?,

    @ColumnInfo(name = "movie_release")
    val movieRelease: String,

    @ColumnInfo(name = "movie_title")
    @NonNull
    val movieTitle: String,

    @ColumnInfo(name = "movie_rating")
    val movieRating: Double,

    @ColumnInfo(name = "is_movies")
    val isMovies: Boolean
)