package com.setianjay.watchme.core.utils

import com.setianjay.watchme.core.data.source.local.persistence.model.MovieNowPlayingEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MoviePopularEntity
import com.setianjay.watchme.core.data.source.remote.model.MoviesResponse.MoviesItem
import com.setianjay.watchme.core.data.source.remote.model.TvResponse.TvShowItem
import com.setianjay.watchme.core.domain.model.Movie

/**
 * convert [MovieNowPlayingEntity] (local model) to [Movie] (use case model) for showing data
 *
 * @return      list of [Movie] (use case model)
 * */
fun List<MovieNowPlayingEntity>.movieNowPlayingEntityToMovie(): List<Movie> {
    return this.map { movieNowPlayingEntity ->
        Movie(
            movieBackdrop = movieNowPlayingEntity.movieBackdrop ?: "",
            movieGenre = movieNowPlayingEntity.movieGenre,
            movieId = movieNowPlayingEntity.movieId,
            movieLanguage = movieNowPlayingEntity.movieLanguage,
            movieOverview = movieNowPlayingEntity.movieOverview,
            moviePopularity = movieNowPlayingEntity.moviePopularity,
            moviePoster = movieNowPlayingEntity.moviePoster ?: "",
            movieRelease = movieNowPlayingEntity.movieRelease,
            movieTitle = movieNowPlayingEntity.movieTitle,
            movieRating = movieNowPlayingEntity.movieRating,
            isMovies = movieNowPlayingEntity.isMovies
        )
    }
}

/**
 * convert [MoviePopularEntity] (local model) to [Movie] (use case model) for showing data
 *
 * @return      list of [Movie] (use case model)
 * */
fun List<MoviePopularEntity>.moviePopularEntityToMovie(): List<Movie> {
    return this.map { moviePopularEntity ->
        Movie(
            movieBackdrop = moviePopularEntity.movieBackdrop ?: "",
            movieGenre = moviePopularEntity.movieGenre,
            movieId = moviePopularEntity.movieId,
            movieLanguage = moviePopularEntity.movieLanguage,
            movieOverview = moviePopularEntity.movieOverview,
            moviePopularity = moviePopularEntity.moviePopularity,
            moviePoster = moviePopularEntity.moviePoster ?: "",
            movieRelease = moviePopularEntity.movieRelease,
            movieTitle = moviePopularEntity.movieTitle,
            movieRating = moviePopularEntity.movieRating,
            isMovies = moviePopularEntity.isMovies
        )
    }
}

/**
 * convert [MoviesItem] (remote model) to [MovieNowPlayingEntity] (local model) for insert data to local
 *
 * @return      list of [MovieNowPlayingEntity] (local model)
 * @note        [MovieNowPlayingEntity.isMovies] true because it is movies
 * */
fun List<MoviesItem>.movieItemToMovieNowPlayingEntity(): List<MovieNowPlayingEntity> {
    return this.map { moviesItem ->
        MovieNowPlayingEntity(
            movieBackdrop = moviesItem.moviesBackdrop,
            movieGenre = DataUtil.getGenres(moviesItem.moviesGenreIds),
            movieId = moviesItem.moviesId,
            movieLanguage = moviesItem.moviesLanguage,
            movieOverview = moviesItem.moviesOverview,
            moviePopularity = moviesItem.moviesPopularity,
            moviePoster = moviesItem.moviesPoster,
            movieRelease = moviesItem.moviesRelease,
            movieTitle = moviesItem.moviesTitle,
            movieRating = moviesItem.moviesRating,
            isMovies = true
        )
    }
}

/**
 * convert [MoviesItem] (remote model) to [MoviePopularEntity] (local model) for inserting data to local
 *
 * @return      list of [MoviePopularEntity] (local model)
 * @note        [MoviePopularEntity.isMovies] true because it is movies
 * */
fun List<MoviesItem>.movieItemToMoviePopularEntity(): List<MoviePopularEntity> {
    return this.map { moviesItem ->
        MoviePopularEntity(
            movieBackdrop = moviesItem.moviesBackdrop,
            movieGenre = DataUtil.getGenres(moviesItem.moviesGenreIds),
            movieId = moviesItem.moviesId,
            movieLanguage = moviesItem.moviesLanguage,
            movieOverview = moviesItem.moviesOverview,
            moviePopularity = moviesItem.moviesPopularity,
            moviePoster = moviesItem.moviesPoster,
            movieRelease = moviesItem.moviesRelease,
            movieTitle = moviesItem.moviesTitle,
            movieRating = moviesItem.moviesRating,
            isMovies = true
        )
    }
}

/**
 * convert [TvShowItem] (remote model) to [MovieNowPlayingEntity] (local model) for inserting data to local
 *
 * @return      list of [MovieNowPlayingEntity] (local model)
 * @note        [MovieNowPlayingEntity.isMovies] false because it is tv
 * */
fun List<TvShowItem>.tvShowItemToMovieNowPlayingEntity(): List<MovieNowPlayingEntity> {
    return this.map { tvShowItem ->
        MovieNowPlayingEntity(
            movieBackdrop = tvShowItem.tvShowBackdrop,
            movieGenre = DataUtil.getGenres(tvShowItem.tvGenreIds),
            movieId = tvShowItem.tvShowId,
            movieLanguage = tvShowItem.tvShowLanguage,
            movieOverview = tvShowItem.tvShowOverview,
            moviePopularity = tvShowItem.tvShowPopularity,
            moviePoster = tvShowItem.tvShowPoster,
            movieRelease = tvShowItem.tvShowRelease,
            movieTitle = tvShowItem.tvShowTitle,
            movieRating = tvShowItem.tvShowRating,
            isMovies = false
        )
    }
}

/**
 * convert [TvShowItem] (remote model) to [MoviePopularEntity] (local model) for inserting data to local
 *
 *
 * @return      list of [MoviePopularEntity] (local model)
 * @note        [MoviePopularEntity.isMovies] false because it is tv
 * */
fun List<TvShowItem>.tvShowItemToMoviePopularEntity(): List<MoviePopularEntity> {
    return this.map { tvShowItem ->
        MoviePopularEntity(
            movieBackdrop = tvShowItem.tvShowBackdrop,
            movieGenre = DataUtil.getGenres(tvShowItem.tvGenreIds),
            movieId = tvShowItem.tvShowId,
            movieLanguage = tvShowItem.tvShowLanguage,
            movieOverview = tvShowItem.tvShowOverview,
            moviePopularity = tvShowItem.tvShowPopularity,
            moviePoster = tvShowItem.tvShowPoster,
            movieRelease = tvShowItem.tvShowRelease,
            movieTitle = tvShowItem.tvShowTitle,
            movieRating = tvShowItem.tvShowRating,
            isMovies = false
        )
    }
}
