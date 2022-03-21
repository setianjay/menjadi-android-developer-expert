package com.setianjay.watchme.presentation.home.adapter.tab

import com.setianjay.watchme.R
import com.setianjay.watchme.presentation.home.content.movies.MoviesFragment
import com.setianjay.watchme.presentation.home.content.tvshow.TvShowFragment

object TabMain {

    fun getTabs(): List<TabItem>{
        return listOf(
            TabItem(MoviesFragment(), "Movies", R.drawable.ic_movie_tab_unselected, R.drawable.ic_movie_tab_selected),
            TabItem(TvShowFragment(), "Tv Show", R.drawable.ic_tv_tab_unselected, R.drawable.ic_tv_tab_selected)
        )
    }
}