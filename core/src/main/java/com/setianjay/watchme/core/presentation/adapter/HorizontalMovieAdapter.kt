package com.setianjay.watchme.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.watchme.core.databinding.ItemMovieHorizontalBinding
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.utils.ViewUtil.load

class HorizontalMovieAdapter(private val listener: OnMovieAdapterListener) :
    RecyclerView.Adapter<HorizontalMovieAdapter.HorizontalMovieViewHolder>() {

    private val listMovie = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMovieViewHolder {
        return HorizontalMovieViewHolder(
            ItemMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalMovieViewHolder, position: Int) {
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

    inner class HorizontalMovieViewHolder(
        private val binding: ItemMovieHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Movie){
            binding.apply {
                ivMoviePoster.load(movie.moviePoster)
                tvMovieRating.text = movie.movieRating.toString()

                root.setOnClickListener {
                    listener.onClick(movie)
                }
            }
        }
    }
}
