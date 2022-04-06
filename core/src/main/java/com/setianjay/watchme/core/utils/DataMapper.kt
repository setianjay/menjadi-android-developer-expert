package com.setianjay.watchme.core.utils

import com.setianjay.watchme.core.data.source.local.persistence.model.MovieFavoriteEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MovieNowPlayingEntity
import com.setianjay.watchme.core.data.source.local.persistence.model.MoviePopularEntity
import com.setianjay.watchme.core.data.source.remote.model.MoviesResponse.MoviesItem
import com.setianjay.watchme.core.data.source.remote.model.TvResponse.TvShowItem
import com.setianjay.watchme.core.domain.model.Movie

/*----------------- Convert for showing data -----------------*/
/**
 * convert list of [MovieFavoriteEntity] (local model) to list of [Movie] (use case model) for showing data
 *
 * @return      list of [Movie] (use case model)
 * */
fun List<MovieFavoriteEntity>.movieFavoriteEntityToMovie(): List<Movie> {
    return this.map { movieFavoriteEntity ->
        Movie(
            movieBackdrop = movieFavoriteEntity.movieBackdrop,
            movieGenre = movieFavoriteEntity.movieGenre,
            movieId = movieFavoriteEntity.movieId,
            movieLanguage = movieFavoriteEntity.movieLanguage,
            movieOverview = movieFavoriteEntity.movieOverview,
            moviePopularity = movieFavoriteEntity.moviePopularity,
            moviePoster = movieFavoriteEntity.moviePoster,
            movieRelease = movieFavoriteEntity.movieRelease,
            movieTitle = movieFavoriteEntity.movieTitle,
            movieRating = movieFavoriteEntity.movieRating,
            isMovies = movieFavoriteEntity.isMovies
        )
    }
}

/**
 * convert list of [MovieNowPlayingEntity] (local model) to list of [Movie] (use case model) for showing data
 *
 * @return      list of [Movie] (use case model)
 * */
fun List<MovieNowPlayingEntity>.movieNowPlayingEntityToMovie(): List<Movie> {
    return this.map { movieNowPlayingEntity ->
        Movie(
            movieBackdrop = movieNowPlayingEntity.movieBackdrop,
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
 * convert list of [MoviePopularEntity] (local model) to list of [Movie] (use case model) for showing data
 *
 * @return      list of [Movie] (use case model)
 * */
fun List<MoviePopularEntity>.moviePopularEntityToMovie(): List<Movie> {
    return this.map { moviePopularEntity ->
        Movie(
            movieBackdrop = moviePopularEntity.movieBackdrop,
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
 * convert [MovieFavoriteEntity] (local model) to [Movie] (use case model) for showing data
 *
 * @return      data of movie representing on [Movie] (use case model)
 * */
fun MovieFavoriteEntity.toMovie(): Movie {
    return Movie(
        movieBackdrop = this.movieBackdrop,
        movieGenre = this.movieGenre,
        movieId = this.movieId,
        movieLanguage = this.movieLanguage,
        movieOverview = this.movieOverview,
        moviePopularity = this.moviePopularity,
        moviePoster = this.moviePoster,
        movieRelease = this.movieRelease,
        movieTitle = this.movieTitle,
        movieRating = this.movieRating,
        isMovies = this.isMovies
    )
}

/**
 * convert [MoviesItem] (remote model) to [Movie] (use case model) for showing data
 *
 * @return      data list of movies representing on [Movie] (use case model)
 * */
fun List<MoviesItem>.moviesItemToMovie(): List<Movie> {
    return this.map { moviesItem ->
        Movie(
            movieBackdrop = moviesItem.moviesBackdrop ?: "",
            movieGenre = DataUtil.getGenreByListId(moviesItem.moviesGenreIds),
            movieId = moviesItem.moviesId,
            movieLanguage = moviesItem.moviesLanguage,
            movieOverview = moviesItem.moviesOverview.ifEmpty { "-" },
            moviePopularity = moviesItem.moviesPopularity,
            moviePoster = moviesItem.moviesPoster ?: "",
            movieRelease = FormatUtil.changeDateFormat(moviesItem.moviesRelease),
            movieTitle = moviesItem.moviesTitle,
            movieRating = moviesItem.moviesRating,
            isMovies = true
        )
    }
}

/**
 * convert [TvShowItem] (remote model) to [Movie] (use case model) for showing data
 *
 * @return      data list of tv representing on [Movie] (use case model)
 * */
fun List<TvShowItem>.tvShowItemToMovie(): List<Movie> {
    return this.map { tvShowItem ->
        Movie(
            movieBackdrop = tvShowItem.tvShowBackdrop ?: "",
            movieGenre = DataUtil.getGenreByListId(tvShowItem.tvGenreIds),
            movieId = tvShowItem.tvShowId,
            movieLanguage = tvShowItem.tvShowLanguage,
            movieOverview = tvShowItem.tvShowOverview.ifEmpty { "-" },
            moviePopularity = tvShowItem.tvShowPopularity,
            moviePoster = tvShowItem.tvShowPoster ?: "",
            movieRelease = FormatUtil.changeDateFormat(tvShowItem.tvShowRelease),
            movieTitle = tvShowItem.tvShowTitle,
            movieRating = tvShowItem.tvShowRating,
            isMovies = false
        )
    }
}


/*----------------- Convert for inserting data to local database -----------------*/

/**
 * convert [MoviesItem] (remote model) to [MovieNowPlayingEntity] (local model) for insert data to local
 *
 * @return      list of [MovieNowPlayingEntity] (local model)
 * @note        [MovieNowPlayingEntity.isMovies] true because it is movies
 * */
fun List<MoviesItem>.movieItemToMovieNowPlayingEntity(): List<MovieNowPlayingEntity> {
    return this.map { moviesItem ->
        MovieNowPlayingEntity(
            movieBackdrop = moviesItem.moviesBackdrop ?: "",
            movieGenre = DataUtil.getGenreByListId(moviesItem.moviesGenreIds),
            movieId = moviesItem.moviesId,
            movieLanguage = moviesItem.moviesLanguage,
            movieOverview = moviesItem.moviesOverview.ifEmpty { "-" },
            moviePopularity = moviesItem.moviesPopularity,
            moviePoster = moviesItem.moviesPoster ?: "",
            movieRelease = FormatUtil.changeDateFormat(moviesItem.moviesRelease),
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
            movieBackdrop = moviesItem.moviesBackdrop ?: "",
            movieGenre = DataUtil.getGenreByListId(moviesItem.moviesGenreIds),
            movieId = moviesItem.moviesId,
            movieLanguage = moviesItem.moviesLanguage,
            movieOverview = moviesItem.moviesOverview.ifEmpty { "-" },
            moviePopularity = moviesItem.moviesPopularity,
            moviePoster = moviesItem.moviesPoster ?: "",
            movieRelease = FormatUtil.changeDateFormat(moviesItem.moviesRelease),
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
            movieBackdrop = tvShowItem.tvShowBackdrop ?: "",
            movieGenre = DataUtil.getGenreByListId(tvShowItem.tvGenreIds),
            movieId = tvShowItem.tvShowId,
            movieLanguage = tvShowItem.tvShowLanguage,
            movieOverview = tvShowItem.tvShowOverview.ifEmpty { "-" },
            moviePopularity = tvShowItem.tvShowPopularity,
            moviePoster = tvShowItem.tvShowPoster ?: "",
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
            movieBackdrop = tvShowItem.tvShowBackdrop ?: "",
            movieGenre = DataUtil.getGenreByListId(tvShowItem.tvGenreIds),
            movieId = tvShowItem.tvShowId,
            movieLanguage = tvShowItem.tvShowLanguage,
            movieOverview = tvShowItem.tvShowOverview.ifEmpty { "-" },
            moviePopularity = tvShowItem.tvShowPopularity,
            moviePoster = tvShowItem.tvShowPoster ?: "",
            movieRelease = tvShowItem.tvShowRelease,
            movieTitle = tvShowItem.tvShowTitle,
            movieRating = tvShowItem.tvShowRating,
            isMovies = false
        )
    }
}

/**
 * convert [Movie] (use case model) to [MovieFavoriteEntity] (local model) for inserting movie favorite to local
 *
 *
 * @return      [MovieFavoriteEntity] (local model)
 * */
fun Movie.toFavoriteEntity(): MovieFavoriteEntity {
    return MovieFavoriteEntity(
        movieBackdrop = this.movieBackdrop,
        movieGenre = this.movieGenre,
        movieId = this.movieId,
        movieLanguage = this.movieLanguage,
        movieOverview = this.movieOverview,
        moviePopularity = this.moviePopularity,
        moviePoster = this.moviePoster,
        movieRelease = this.movieRelease,
        movieTitle = this.movieTitle,
        movieRating = this.movieRating,
        isMovies = this.isMovies
    )
}
