package com.techmahindra.techmEmployeePortal.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techmahindra.techmEmployeePortal.R


/* *
* LoadImage - load image using @Glide lib
* #uri - Link to load image from path
* */
fun ImageView.loadImage(item : Drawable) {
    val options = RequestOptions()
        .placeholder(R.mipmap.ic_launcher_round)
        .circleCrop()
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(item)
        .into(this)
}