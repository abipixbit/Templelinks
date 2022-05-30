package com.example.templelinks

import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.model.response.TemplesResponse
import kotlin.math.sign

class EventBuss {

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