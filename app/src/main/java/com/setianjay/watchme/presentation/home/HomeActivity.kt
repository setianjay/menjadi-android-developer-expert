package com.setianjay.watchme.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.setianjay.watchme.R
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.presentation.adapter.OnMovieAdapterListener
import com.setianjay.watchme.core.utils.ViewUtil.customSelectedTab
import com.setianjay.watchme.core.utils.ViewUtil.show
import com.setianjay.watchme.databinding.ActivityHomeBinding
import com.setianjay.watchme.presentation.detail.DetailMovieActivity
import com.setianjay.watchme.presentation.home.adapter.slider.ViewSliderHomeAdapter
import com.setianjay.watchme.presentation.home.adapter.tab.ViewTabHomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var viewPagerSliderHomeAdapter: ViewSliderHomeAdapter
    private lateinit var viewTabHomeAdapter: ViewTabHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initListener()
        setupObserver()
        setupViewPagerSlider()
        setupViewTabMain()
        setupSelectedCustomTab()
    }

    /**
     * setup viewpager2 as slider
     * */
    private fun setupViewPagerSlider() {
        viewPagerSliderHomeAdapter = ViewSliderHomeAdapter(object : OnMovieAdapterListener {
            override fun onClick(movie: Movie) {
                startActivity(
                    DetailMovieActivity.navigateToDetailMovieActivity(
                        this@HomeActivity,
                        movie
                    )
                )
            }
        })
        binding.vpSlider.adapter = viewPagerSliderHomeAdapter
    }

    /**
     * setup tab layout and view pager as the main content
     * */
    private fun setupViewTabMain() {
        viewTabHomeAdapter = ViewTabHomeAdapter(supportFragmentManager, this)

        //view pager listener when new page at tab has selected, bring position value
        val mViewPagerListener = ViewPagerPageChangeListener(object : ViewPagerPageChangeCallback {
            override fun onPageSelected(position: Int) {
                setupSelectedCustomTab(position)
            }
        })

        //connect tab with view pager
        binding.apply {
            vpMain.adapter = viewTabHomeAdapter
            vpMain.addOnPageChangeListener(mViewPagerListener)
            tlMain.setupWithViewPager(vpMain)
        }
    }

    /**
     * function to create custom selected tab in tab-layout
     *
     * @param position      number of position the current tab
     * */
    private fun setupSelectedCustomTab(position: Int = 0) {
        binding.tlMain.customSelectedTab(position, viewTabHomeAdapter)
    }

    /**
     * setup observer for observe value from view model
     * */
    private fun setupObserver() {
        homeViewModel.getMovieSlider().observe(this) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.pbSlider.show(true)
                }
                is Resource.Success -> {
                    binding.pbSlider.show(false)
                    result.data?.let { movieList ->
                        viewPagerSliderHomeAdapter.setSlider(movieList)
                    }
                }
                is Resource.Error -> {
                    binding.pbSlider.show(false)
                }
            }
        }
    }

    private fun initListener(){
        //when search section has clicked
        binding.llSearch.setOnClickListener {
            //if zero = movies, otherwise tv
            val currentTabPosition = binding.tlMain.selectedTabPosition
            try {
                Intent(
                    this,
                    Class.forName("com.setianjay.watchme.search.presentation.SearchActivity")
                ).also {
                    it.putExtra(SEARCH_EXTRA, currentTabPosition)
                    startActivity(it)
                }
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.module_not_found), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    /**
     * create custom menu
     * */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    /**
     * function to handle when menu item has clicked
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //when menu favorite has selected
            R.id.action_menu_favorite -> {
                try {
                    Intent(
                        this,
                        Class.forName("com.setianjay.watchme.favorite.presentation.FavoriteActivity")
                    ).also {
                        startActivity(it)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, getString(R.string.module_not_found), Toast.LENGTH_SHORT)
                        .show()
                }
            }
            //when menu settings has selected
            R.id.action_menu_settings -> {
                Toast.makeText(this, getString(R.string.settings), Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        //key for sending value to search module
        const val SEARCH_EXTRA = "search_extra"
    }
}