package com.example.templelinks.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.templelinks.R
import com.google.android.material.snackbar.Snackbar

fun View.glide(url : String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.loading_image).centerInside()
        .into(imageView)
}

fun View.snackBarLike(text : String) {
    Snackbar.make(this,text,Snackbar.LENGTH_SHORT)
        .setBackgroundTint(ContextCompat.getColor(context,R.color.app_color))
        .show()
}

fun View.navigation(id : Int) {
    Navigation.findNavController(this).navigate(id)
}