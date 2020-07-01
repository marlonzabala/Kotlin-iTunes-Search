package com.app.itunes.search.extensions

import android.widget.ImageView
import com.app.itunes.search.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(imageAddress: String) {
    val requestOption = RequestOptions()
        .placeholder(R.mipmap.ic_launcher)
        .centerCrop()
        .error(R.mipmap.placeholder)
        .dontTransform()
        .dontAnimate()

    Glide.with(this)
        .load(imageAddress)
        .apply(requestOption)
        .into(this)
}