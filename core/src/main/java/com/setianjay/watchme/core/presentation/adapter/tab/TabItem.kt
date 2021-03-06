package com.setianjay.watchme.core.presentation.adapter.tab

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

data class TabItem(
    val fragment: Fragment,
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int
)
