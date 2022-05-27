package com.example.templelinks.extensions

import android.content.Context
import android.widget.Toast
import androidx.navigation.Navigation

fun Context.toast(des : String) {
    Toast.makeText(this,des,Toast.LENGTH_SHORT)
        .show()
}

