package com.setianjay.watchme.core.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.setianjay.watchme.core.R
import com.setianjay.watchme.core.constants.RemoteConst

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
            .into(this)
    }

    /**
     * to load image with glide
     *
     * @param image     filename of image
     * note:            the placeholder using image
     * */
    fun ImageView.load(image: String) {
        if(image.isEmpty()){
            Glide.with(this.context)
                .load(image)
                .apply(RequestOptions.centerCropTransform())
                .placeholder(R.drawable.ic_no_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }else{
            Glide.with(this.context)
                .load(RemoteConst.IMAGE_URL_ORIGINAL + image)
                .apply(RequestOptions.centerCropTransform())
                .placeholder(R.drawable.ic_movie_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }
    }

    /**
     * to show or hide a view
     *
     * @param isShowing     true means view show, otherwise hide
     * */
    fun View.show(isShowing: Boolean) {
        this.visibility = if (isShowing) View.VISIBLE else View.GONE
    }
}