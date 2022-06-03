package com.example.templelinks

import android.util.Log
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.model.response.TemplesResponse
import com.example.templelinks.enums.FavEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.shareIn
import kotlin.math.sign

class FavouriteEventBus {

    private val _favouriteEvent = MutableSharedFlow<FavEvent>()
    val favouriteEvent = _favouriteEvent.asSharedFlow()


    suspend fun favourite(event : FavEvent) {
        _favouriteEvent.emit(event)
    }



//
//    fun setFav(currentTempleList : List<Temple>) {
//        templeList = currentTempleList
//        templeList[0].isFavourite = true
//
//    }
//
//    fun deleteFav(currentTempleList : List<Temple>) {
//        templeList = currentTempleList
//        templeList[0].isFavourite = false
//    }
}