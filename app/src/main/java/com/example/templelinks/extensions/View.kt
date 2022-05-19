package com.example.templelinks.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.templelinks.R

fun View.glide(url : String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.loading_image)
        .into(imageView)
}