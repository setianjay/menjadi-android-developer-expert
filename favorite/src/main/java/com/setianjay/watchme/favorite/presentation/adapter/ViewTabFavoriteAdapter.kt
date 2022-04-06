package com.setianjay.watchme.favorite.presentation.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.setianjay.watchme.core.presentation.adapter.tab.BaseViewTabAdapter
import com.setianjay.watchme.core.presentation.adapter.tab.TabItem
import com.setianjay.watchme.favorite.presentation.content.FavoriteMovieFragment

class ViewTabFavoriteAdapter(fragmentManager: FragmentManager, context: Context) :
    BaseViewTabAdapter(fragmentManager, context) {

    override fun getFragments(): List<TabItem> {
        return listOf(
            TabItem(
                FavoriteMovieFragment(true),
                context.resources.getString(com.setianjay.watchme.core.R.string.movies),
                com.setianjay.watchme.R.drawable.ic_movie_tab_unselected,
                com.setianjay.watchme.R.drawable.ic_movie_tab_selected
            ),
            TabItem(
                FavoriteMovieFragment(false),
                context.resources.getString(com.setianjay.watchme.core.R.string.tv),
                com.setianjay.watchme.R.drawable.ic_tv_tab_unselected,
                com.setianjay.watchme.R.drawable.ic_tv_tab_selected
            ),
        )
    }
}