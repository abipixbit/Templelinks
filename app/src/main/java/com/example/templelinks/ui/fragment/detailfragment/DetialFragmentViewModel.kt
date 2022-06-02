package com.example.templelinks.ui.fragment.detailfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.repository.FavouriteRepository
import kotlinx.coroutines.launch

class DetailFragmentViewModel : ViewModel() {

   private val favouriteRepository = FavouriteRepository()

    fun setFavourite (id : Int?) {
        viewModelScope.launch {
            favouriteRepository.setFavourite(id)
        }
    }

    fun deleteFavourite(id : Int?) {
        viewModelScope.launch {
           favouriteRepository.deleteFavourite(id)
        }
    }
}