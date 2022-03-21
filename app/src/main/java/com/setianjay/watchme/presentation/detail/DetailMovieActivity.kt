package com.setianjay.watchme.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.setianjay.watchme.R
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.utils.ViewUtil.load
import com.setianjay.watchme.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupData()
    }

    /**
     * setup movie data from intent
     * */
    private fun setupData(){
        val movie = intent.extras?.getParcelable<Movie>(EXTRA_DETAIL)
        movie?.let {
            //check user
            populateDataDetail(it)
        }
    }

    /**
     * display detail of movie to screen
     *
     * @param movie     data of movie
     * */
    private fun populateDataDetail(movie: Movie){
        binding.apply {
            ivMoviePoster.load(movie.movieBackdrop)
            tvMovieTitle.text = movie.movieTitle
            tvMovieGenre.text = movie.movieGenre
            tvMovieLanguage.text = resources.getString(R.string.movie_language, movie.movieLanguage)
            tvMoviePopularity.text = resources.getString(R.string.movie_popularity, movie.moviePopularity.toString())
            tvMovieRelease.text = resources.getString(com.setianjay.watchme.core.R.string.release, movie.movieRelease)
            rbMovie.rating = movie.movieRating.toFloat()
            tvRating.text = movie.movieRating.toString()
            tvMovieOverview.text = movie.movieOverview
        }
    }

    companion object{
        private const val EXTRA_DETAIL = "extra_detail"

        /**
         * to navigate to detail screen
         *
         * @param context   context
         * @param movie     data of movie
         * */
        fun navigateToDetailMovieActivity(context: Context, movie: Movie): Intent{
            return Intent(context, DetailMovieActivity::class.java).also {
                it.putExtra(EXTRA_DETAIL, movie)
            }
        }
    }
}