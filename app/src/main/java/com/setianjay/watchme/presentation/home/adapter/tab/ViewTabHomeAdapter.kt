package com.setianjay.watchme.presentation.home.adapter.tab

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.setianjay.watchme.core.R
import com.setianjay.watchme.core.presentation.adapter.tab.BaseViewTabAdapter
import com.setianjay.watchme.core.presentation.adapter.tab.TabItem
import com.setianjay.watchme.presentation.home.content.movies.MoviesFragment
import com.setianjay.watchme.presentation.home.content.tvshow.TvShowFragment

class ViewTabHomeAdapter(
    fragmentManager: FragmentManager,
    context: Context
) :
    BaseViewTabAdapter(fragmentManager, context) {

    override fun getFragments(): List<TabItem> {
        return listOf(
            TabItem(
                MoviesFragment(),
                context.resources.getString(R.string.movies),
                com.setianjay.watchme.R.drawable.ic_movie_tab_unselected,
                com.setianjay.watchme.R.drawable.ic_movie_tab_selected
            ),
            TabItem(
                TvShowFragment(),
                context.resources.getString(R.string.tv),
                com.setianjay.watchme.R.drawable.ic_tv_tab_unselected,
                com.setianjay.watchme.R.drawable.ic_tv_tab_selected
            )
        )
    }

}