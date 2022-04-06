package com.setianjay.watchme.presentation.home

import androidx.viewpager.widget.ViewPager

class ViewPagerPageChangeListener(private val viewPagerPageChangeCallback: ViewPagerPageChangeCallback) :
    ViewPager.OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        /* not implemented */
    }

    override fun onPageSelected(position: Int) {
        viewPagerPageChangeCallback.onPageSelected(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        /* not implemented */
    }
}