package com.setianjay.watchme.presentation.home.adapter.slider

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.presentation.adapter.OnMovieAdapterListener
import com.setianjay.watchme.core.utils.ViewUtil.loadImageSlider
import com.setianjay.watchme.databinding.ItemHomeSliderBinding

class ViewSliderHomeAdapter(private val adapterListener: OnMovieAdapterListener) :
    RecyclerView.Adapter<ViewSliderHomeAdapter.ViewHomeSliderViewHolder>() {
    private val moviesList = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHomeSliderViewHolder {
        return ViewHomeSliderViewHolder(
            ItemHomeSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHomeSliderViewHolder, position: Int) {
        val movies: Movie = moviesList[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setSlider(sliderContent: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(sliderContent)
        notifyDataSetChanged()
    }

    inner class ViewHomeSliderViewHolder(private val binding: ItemHomeSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movie) {
            binding.apply {
                ivPosterSlider.loadImageSlider(movies.movieBackdrop)
                tvTitleSlider.text = movies.movieTitle
                tvOverviewSlider.text = movies.movieOverview

                //click listener
                root.setOnClickListener {
                    adapterListener.onClick(movies)
                }
            }
        }
    }
}