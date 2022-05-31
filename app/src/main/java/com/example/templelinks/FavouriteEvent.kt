package com.example.templelinks

import android.util.Log
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.model.response.TemplesResponse
import kotlin.math.sign

class FavouriteEvent {

    var mediumTemple = listOf<Temple>()

    private lateinit var templeList : List<Temple>

    fun setFav(currentTempleList : List<Temple>) {
        templeList = currentTempleList
        templeList[0].isFavourite = true

    }

    fun deleteFav(currentTempleList : List<Temple>) {
        templeList = currentTempleList
        templeList[0].isFavourite = false
    }

}