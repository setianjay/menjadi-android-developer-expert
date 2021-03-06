package com.setianjay.watchme.core.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.setianjay.watchme.core.R
import com.setianjay.watchme.core.constants.RemoteConst
import com.setianjay.watchme.core.presentation.adapter.tab.BaseViewTabAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ViewUtil {

    /**
     * circular progress as indicator process on glide
     *
     * @param context   context
     * */
    private fun createCircularProgressDrawable(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 4f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    /**
     * to load image slider with glide
     *
     * @param image     filename of image
     * note:            the placeholder using circular progress drawable
     * */
    fun ImageView.loadImageSlider(image: String) {
        Glide.with(this.context)
            .load(RemoteConst.IMAGE_URL_ORIGINAL + image)
            .apply(RequestOptions.centerCropTransform())
            .placeholder(createCircularProgressDrawable(this.context))
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_no_image)
            .into(this)
    }

    /**
     * to load image with glide
     *
     * @param image     filename of image
     * note:            the placeholder using image
     * */
    fun ImageView.load(image: String) {
        Glide.with(this.context)
                .load(RemoteConst.IMAGE_URL_ORIGINAL + image)
                .apply(RequestOptions.centerCropTransform())
                .placeholder(R.drawable.ic_movie_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_no_image)
                .into(this)
    }

    /**
     * to show or hide a view
     *
     * @param isShowing     true means view show, otherwise hide
     * */
    fun View.show(isShowing: Boolean) {
        this.visibility = if (isShowing) View.VISIBLE else View.GONE
    }

    /**
     * to convert String to HtmlSpan
     * */
    private fun String.toHtmlSpan(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }

    /**
     * to get string value on strings resource there is an html tag inside
     * */
    fun Context.getHtmlSpannedString(@StringRes id: Int, vararg formatArgs: Any): Spanned = getString(id, *formatArgs).toHtmlSpan()

    /**
     * to create custom selected tab
     *
     * @param position          indicate as current position of tab
     * @param viewTabAdapter    adapter for view pager
     * */
    fun TabLayout.customSelectedTab(position: Int, viewTabAdapter: BaseViewTabAdapter){
        CoroutineScope(Dispatchers.Default).launch {
            //loop based on tab count on tab layout
            for (i in 0 until this@customSelectedTab.tabCount) {
                withContext(Dispatchers.Main) {
                    val tab = this@customSelectedTab.getTabAt(i)
                    //if i same with param position, selected active. otherwise selected un-active
                    if (i == position) {
                        tab?.customView = null
                        tab?.customView = viewTabAdapter.viewTabSelected(i)
                    } else {
                        this@customSelectedTab.getTabAt(i).apply {
                            tab?.customView = null
                            tab?.customView = viewTabAdapter.viewTabUnselected(i)
                        }
                    }
                }
            }
        }
    }
}