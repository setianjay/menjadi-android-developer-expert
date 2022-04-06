package com.setianjay.watchme.core.presentation.adapter.tab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.setianjay.watchme.core.R
import com.setianjay.watchme.core.databinding.TabCustomBinding

abstract class BaseViewTabAdapter(
    fragmentManager: FragmentManager,
    protected val context: Context
) : FragmentStatePagerAdapter(fragmentManager) {

    protected abstract fun getFragments(): List<TabItem>

    override fun getCount(): Int {
        return getFragments().size
    }

    override fun getItem(position: Int): Fragment {
        return getFragments()[position].fragment
    }

    fun viewTabUnselected(position: Int): View {
        val viewCustom = TabCustomBinding.inflate(LayoutInflater.from(context))
        viewCustom.apply {
            ivTab.setImageResource(getFragments()[position].icon)
            tvTab.apply {
                text = getFragments()[position].title
                setTextColor(ContextCompat.getColor(context, R.color.soft_gray))
            }
        }

        return viewCustom.root
    }

    fun viewTabSelected(position: Int): View {
        val viewCustom = TabCustomBinding.inflate(LayoutInflater.from(context))
        viewCustom.apply {
            ivTab.setImageResource(getFragments()[position].selectedIcon)
            tvTab.apply {
                text = getFragments()[position].title
                setTextColor(ContextCompat.getColor(context, R.color.light_blue))
            }
        }

        return viewCustom.root
    }
}