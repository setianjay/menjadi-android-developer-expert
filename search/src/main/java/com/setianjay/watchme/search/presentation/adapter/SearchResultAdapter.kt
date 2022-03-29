package com.setianjay.watchme.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.presentation.adapter.OnMovieAdapterListener
import com.setianjay.watchme.core.utils.FormatUtil
import com.setianjay.watchme.core.utils.ViewUtil.load
import com.setianjay.watchme.search.databinding.ItemSearchResultBinding

class SearchResultAdapter(
    private val listener: OnMovieAdapterListener
) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    private val listMovie = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    fun setSearchResult(result: List<Movie>) {
        this.listMovie.clear()
        this.listMovie.addAll(result)
        notifyDataSetChanged()
    }

    inner class SearchResultViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(movie: Movie){
                binding.apply {
                    ivMoviePoster.load(movie.moviePoster)
                    tvMovieTitle.text = movie.movieTitle
                    tvMovieYear.text = movie.movieRelease
                    tvMovieGenre.text = movie.movieGenre
                    tvMovieRating.text = movie.movieRating.toString()

                    root.setOnClickListener {
                        listener.onClick(movie)
                    }
                }
            }
    }
}
