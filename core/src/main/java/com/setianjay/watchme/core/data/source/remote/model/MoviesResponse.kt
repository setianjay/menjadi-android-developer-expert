package com.setianjay.watchme.core.data.source.remote.model


import com.google.gson.annotations.SerializedName

/**
 * network model for holding a response from movies API
 * */
data class MoviesResponse(
    @SerializedName("results")
    val moviesItems: List<MoviesItem>
){
    data class MoviesItem(
        @SerializedName("genre_ids")
        val movieGenreIds: List<Int>,

        @SerializedName("id")
        val movieId: Long,

        @SerializedName("original_language")
        val movieLanguage: String,

        @SerializedName("overview")
        val movieOverview: String,

        @SerializedName("popularity")
        val moviePopularity: Double,

        @SerializedName("poster_path")
        val moviePoster: String,

        @SerializedName("release_date")
        val movieRelease: String,

        @SerializedName("title")
        val movieTitle: String,

        @SerializedName("vote_average")
        val movieRating: Double
    )
}