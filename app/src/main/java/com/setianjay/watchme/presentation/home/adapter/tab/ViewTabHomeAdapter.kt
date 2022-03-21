package com.setianjay.watchme.presentation.home.adapter.tab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.setianjay.watchme.core.R
import com.setianjay.watchme.databinding.TabCustomBinding

class ViewTabHomeAdapter(fragmentManager: FragmentManager, private val context: Context) :
    FragmentStatePagerAdapter(fragmentManager) {
//    private val viewCustom get() = TabCustomBinding.inflate(LayoutInflater.from(context))
    private val fragments: List<TabItem> = TabMain.getTabs()

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position].fragment
    }

    fun viewTabUnselected(position: Int): View {
        val viewCustom = TabCustomBinding.inflate(LayoutInflater.from(context))
        viewCustom.apply {
            ivTab.setImageResource(fragments[position].icon)
            tvTab.apply {
                text = fragments[position].title
                setTextColor(ContextCompat.getColor(context, R.color.soft_gray))
            }
        }

        return viewCustom.root
    }

    fun viewTabSelected(position: Int): View {
        val viewCustom = TabCustomBinding.inflate(LayoutInflater.from(context))
        viewCustom.apply {
            ivTab.setImageResource(fragments[position].selectedIcon)
            tvTab.apply {
                text = fragments[position].title
                setTextColor(ContextCompat.getColor(context, R.color.light_blue))
            }
        }

        return viewCustom.root
    }
}