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
        @SerializedName("backdrop_path")
        val moviesBackdrop: String?,

        @SerializedName("genre_ids")
        val moviesGenreIds: List<Int>,

        @SerializedName("id")
        val moviesId: Long,

        @SerializedName("original_language")
        val moviesLanguage: String,

        @SerializedName("overview")
        val moviesOverview: String,

        @SerializedName("popularity")
        val moviesPopularity: Double,

        @SerializedName("poster_path")
        val moviesPoster: String?,

        @SerializedName("release_date")
        val moviesRelease: String,

        @SerializedName("title")
        val moviesTitle: String,

        @SerializedName("vote_average")
        val moviesRating: Double
    )
}