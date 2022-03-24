package com.setianjay.watchme.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.setianjay.watchme.R
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.utils.ViewUtil.getHtmlSpannedString
import com.setianjay.watchme.core.utils.ViewUtil.load
import com.setianjay.watchme.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    private var isMovieFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupData()
    }


    /**
     * setup data movie
     * */
    private fun setupData() {
        val movie = intent.extras?.getParcelable<Movie>(EXTRA_DETAIL)
        movie?.let {
            checkMovieIsFavorite(it.movieId)
            initListener(it)
            populateDataDetail(it)
        }
    }

    /**
     * check whether is movie has favorite before
     *
     * @param movieId       movie id to check in database whether movie is there or not
     * @output              if there, image favorite set to be active. otherwise un-active
     * */
    private fun checkMovieIsFavorite(movieId: Long) {
        detailViewModel.checkMovieIsFavorite(movieId).observe(this) { movie ->
            if (movie != null) {
                binding.fbFavorite.setImageResource(R.drawable.ic_heart_active)
                isMovieFavorite = true
            } else {
                binding.fbFavorite.setImageResource(R.drawable.ic_heart_unactive)
            }
        }
    }


    /**
     * init listener which is in activity details
     *
     * @param movie     data of movie
     * */
    private fun initListener(movie: Movie) {
        binding.apply {
            //favorite listener
            fbFavorite.setOnClickListener {
                isMovieFavorite = if (isMovieFavorite) {
                    //remove from favorite and set isMovieFavorite to false
                    detailViewModel.unsetMovieFavorite(movie)
                    fbFavorite.setImageResource(R.drawable.ic_heart_unactive)
                    false
                } else {
                    //add to favorite and set isMovieFavorite to true
                    detailViewModel.setMovieFavorite(movie)
                    fbFavorite.setImageResource(R.drawable.ic_heart_active)
                    true
                }
                showSnackBar(movie.movieTitle, isMovieFavorite)
            }
        }
    }

    /**
     * display detail of movie to screen
     *
     * @param movie     data of movie
     * */
    private fun populateDataDetail(movie: Movie) {
        binding.apply {
            ivMoviePoster.load(movie.movieBackdrop)
            tvMovieTitle.text = movie.movieTitle
            tvMovieGenre.text = movie.movieGenre.ifEmpty { resources.getString(R.string.no_genre) }
            tvMovieLanguage.text = resources.getString(R.string.movie_language, movie.movieLanguage)
            tvMoviePopularity.text =
                resources.getString(R.string.movie_popularity, movie.moviePopularity.toString())
            tvMovieRelease.text =
                resources.getString(com.setianjay.watchme.core.R.string.release, movie.movieRelease)
            rbMovie.rating = movie.movieRating.toFloat()
            tvRating.text = movie.movieRating.toString()
            tvMovieOverview.text =
                movie.movieOverview.ifEmpty { resources.getString(R.string.no_overview) }
        }
    }

    /**
     * show snackBar as message of favorite action
     *
     * @param movieTitle    title of movie
     * @param state         state of favorite action, true means state add. otherwise state remove
     * */
    private fun showSnackBar(movieTitle: String, state: Boolean) {
        val message = if (state) getHtmlSpannedString(
            R.string.add_favorite,
            movieTitle
        ) else getHtmlSpannedString(R.string.remove_favorite, movieTitle)

        val snackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackBar.view.apply {
            setBackgroundColor(
                ContextCompat.getColor(
                    this@DetailMovieActivity,
                    com.setianjay.watchme.core.R.color.light_blue
                )
            )
        }
        snackBar.show()
    }

    companion object {
        private const val EXTRA_DETAIL = "extra_detail"

        /**
         * navigate to detail screen
         *
         * @param context   context
         * @param movie     data of movie
         * */
        fun navigateToDetailMovieActivity(context: Context, movie: Movie): Intent {
            return Intent(context, DetailMovieActivity::class.java).also {
                it.putExtra(EXTRA_DETAIL, movie)
            }
        }
    }
}