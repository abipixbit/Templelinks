package com.example.templelinks.extensions

import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

fun DialogFragment.setFullScreen() {
    dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
}