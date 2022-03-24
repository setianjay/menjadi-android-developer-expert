package com.setianjay.watchme.favorite.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.setianjay.watchme.core.utils.ViewUtil.customSelectedTab
import com.setianjay.watchme.favorite.databinding.ActivityFavoriteBinding
import com.setianjay.watchme.favorite.presentation.adapter.ViewTabFavoriteAdapter
import com.setianjay.watchme.presentation.home.ViewPagerPageChangeCallback
import com.setianjay.watchme.presentation.home.ViewPagerPageChangeListener

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewTabFavoriteAdapter: ViewTabFavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViewTabFavorite()
        setupSelectedCustomTab()
    }

    /**
     * setup tab layout and view pager as the favorite content
     * */
    private fun setupViewTabFavorite(){
        viewTabFavoriteAdapter = ViewTabFavoriteAdapter(supportFragmentManager, this)

        //view pager listener when new page at tab has selected, bring position value
        val mViewPagerListener = ViewPagerPageChangeListener(object : ViewPagerPageChangeCallback {
            override fun onPageSelected(position: Int) {
                setupSelectedCustomTab(position)
            }
        })

        //connect tab with view pager
        binding.apply {
            vpFavorite.adapter = viewTabFavoriteAdapter
            vpFavorite.addOnPageChangeListener(mViewPagerListener)
            tlFavorite.setupWithViewPager(vpFavorite)
        }
    }

    /**
     * function to create custom selected tab in tab-layout
     *
     * @param position      number of position the current tab
     * */
    private fun setupSelectedCustomTab(position: Int = 0) {
        binding.tlFavorite.customSelectedTab(position, viewTabFavoriteAdapter)
    }
}