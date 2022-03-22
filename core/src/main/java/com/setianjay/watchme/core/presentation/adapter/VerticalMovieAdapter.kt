package com.setianjay.watchme.core.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.watchme.core.R
import com.setianjay.watchme.core.databinding.ItemMovieVerticalBinding
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.utils.FormatUtil
import com.setianjay.watchme.core.utils.ViewUtil.load

class VerticalMovieAdapter(private val context: Context, private val listener: OnMovieAdapterListener) :
    RecyclerView.Adapter<VerticalMovieAdapter.VerticalMovieViewHolder>() {

    private val listMovie = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalMovieViewHolder {
        return VerticalMovieViewHolder(
            ItemMovieVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VerticalMovieViewHolder, position: Int) {
        val movie: Movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    fun setDataMovie(movieList: List<Movie>) {
        listMovie.clear()
        listMovie.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class VerticalMovieViewHolder(
        private val binding: ItemMovieVerticalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                ivMoviePoster.load(movie.movieBackdrop)
                tvMovieTitle.text = movie.movieTitle
                tvMovieGenre.text = movie.movieGenre
                tvMovieRelease.text = context.resources.getString(
                    R.string.release,
                    FormatUtil.changeDateFormat(movie.movieRelease)
                )
                rbMovie.rating = movie.movieRating.toFloat()
                tvRating.text = movie.movieRating.toString()

                root.setOnClickListener {
                    listener.onClick(movie)
                }
            }
        }
    }
}
