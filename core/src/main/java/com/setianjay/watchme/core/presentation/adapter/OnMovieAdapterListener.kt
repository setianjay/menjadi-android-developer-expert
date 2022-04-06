package com.setianjay.watchme.core.presentation.adapter

import com.setianjay.watchme.core.domain.model.Movie

interface OnMovieAdapterListener {

    fun onClick(movie: Movie)
}