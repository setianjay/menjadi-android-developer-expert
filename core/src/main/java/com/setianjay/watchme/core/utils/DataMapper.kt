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
            movieGenre = movieNowPlayingEntity.movieGenre,
            movieId = movieNowPlayingEntity.movieId,
            movieLanguage = movieNowPlayingEntity.movieLanguage,
            movieOverview = movieNowPlayingEntity.movieOverview,
            moviePopularity = movieNowPlayingEntity.moviePopularity,
            moviePoster = movieNowPlayingEntity.moviePoster,
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
            movieGenre = moviePopularEntity.movieGenre,
            movieId = moviePopularEntity.movieId,
            movieLanguage = moviePopularEntity.movieLanguage,
            movieOverview = moviePopularEntity.movieOverview,
            moviePopularity = moviePopularEntity.moviePopularity,
            moviePoster = moviePopularEntity.moviePoster,
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
    return this.map { movieItem ->
        MovieNowPlayingEntity(
            movieGenre = DataUtil.getGenres(movieItem.movieGenreIds),
            movieId = movieItem.movieId,
            movieLanguage = movieItem.movieLanguage,
            movieOverview = movieItem.movieOverview,
            moviePopularity = movieItem.moviePopularity,
            moviePoster = movieItem.moviePoster,
            movieRelease = movieItem.movieRelease,
            movieTitle = movieItem.movieTitle,
            movieRating = movieItem.movieRating,
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
    return this.map { movieItem ->
        MoviePopularEntity(
            movieGenre = DataUtil.getGenres(movieItem.movieGenreIds),
            movieId = movieItem.movieId,
            movieLanguage = movieItem.movieLanguage,
            movieOverview = movieItem.movieOverview,
            moviePopularity = movieItem.moviePopularity,
            moviePoster = movieItem.moviePoster,
            movieRelease = movieItem.movieRelease,
            movieTitle = movieItem.movieTitle,
            movieRating = movieItem.movieRating,
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