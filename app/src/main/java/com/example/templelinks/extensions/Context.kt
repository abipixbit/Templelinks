package com.example.templelinks.extensions

import android.content.Context
import android.widget.Toast

fun Context.toast(des : String) {
    Toast.makeText(this,des,Toast.LENGTH_SHORT)
        .show()
}