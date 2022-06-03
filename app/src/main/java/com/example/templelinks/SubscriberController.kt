package com.example.templelinks

import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.enums.FavEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SubscriberController(private val currentTempleList : List<Temple>) : KoinComponent {

    private val favEventBus by inject<FavouriteEventBus>()
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            favEventBus.favouriteEvent.filter {
                it == FavEvent.FAVOURITE
            } .collectLatest { addedToLike() }
        }
    }

    private fun addedToLike() {

    }


}