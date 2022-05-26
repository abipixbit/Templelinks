package com.example.templelinks

import android.app.Application
import android.content.Context


class TempleApplication : Application() {

    //Add in Manifest Also
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        appContext = applicationContext
        super.onCreate()

    }
}