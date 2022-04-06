package com.setianjay.watchme.core.data.source.remote.model


import com.google.gson.annotations.SerializedName

/**
 * network model for holding a response from tv API
 * */
data class TvResponse(
    @SerializedName("results")
    val tvShowItems: List<TvShowItem>
) {
    data class TvShowItem(
        @SerializedName("backdrop_path")
        val tvShowBackdrop: String?,

        @SerializedName("first_air_date")
        val tvShowRelease: String,

        @SerializedName("genre_ids")
        val tvGenreIds: List<Int>,

        @SerializedName("id")
        val tvShowId: Long,

        @SerializedName("name")
        val tvShowTitle: String,

        @SerializedName("original_language")
        val tvShowLanguage: String,

        @SerializedName("overview")
        val tvShowOverview: String,

        @SerializedName("popularity")
        val tvShowPopularity: Double,

        @SerializedName("poster_path")
        val tvShowPoster: String?,

        @SerializedName("vote_average")
        val tvShowRating: Double
    )
}