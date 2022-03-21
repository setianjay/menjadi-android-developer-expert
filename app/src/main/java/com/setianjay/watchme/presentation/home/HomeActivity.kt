package com.setianjay.watchme.presentation.home

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.setianjay.watchme.R
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.utils.ViewUtil.show
import com.setianjay.watchme.databinding.ActivityHomeBinding
import com.setianjay.watchme.presentation.home.adapter.slider.ViewSliderHomeAdapter
import com.setianjay.watchme.presentation.home.adapter.tab.ViewTabHomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        setupObserver()
        setupViewPagerSlider()
        setupViewTabMain()
        setupSelectedCustomTab()
    }

    /**
     * setup viewpager2 as slider
     * */
    private fun setupViewPagerSlider() {
        viewPagerSliderHomeAdapter = ViewSliderHomeAdapter()
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
     * function to handle selected tab in tab-layout
     *
     * @param position      number of position the current tab
     * */
    private fun setupSelectedCustomTab(position: Int = 0) {
        CoroutineScope(Dispatchers.Default).launch {
            //loop based on tab count on tab layout
            for (i in 0 until binding.tlMain.tabCount) {
                withContext(Dispatchers.Main) {
                    //if i same with param position, selected active. otherwise selected un-active
                    if (i == position) {
                        binding.tlMain.getTabAt(i).apply {
                            this?.customView = null
                            this?.customView = viewTabHomeAdapter.viewTabSelected(i)
                        }
                    } else {
                        binding.tlMain.getTabAt(i).apply {
                            this?.customView = null
                            this?.customView = viewTabHomeAdapter.viewTabUnselected(i)
                        }
                    }
                }
            }
        }
    }

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}