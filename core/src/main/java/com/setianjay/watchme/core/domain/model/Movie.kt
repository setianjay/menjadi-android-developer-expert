package com.setianjay.watchme.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieGenre: String,
    val movieId: Long,
    val movieLanguage: String,
    val movieOverview: String,
    val moviePopularity: Double,
    val moviePoster: String,
    val movieRelease: String,
    val movieTitle: String,
    val movieRating: Double,
    val isMovies: Boolean
): Parcelable