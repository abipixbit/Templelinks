package com.example.templelinks.ui.fragment.templeDetailFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.repository.FavouriteRepository
import kotlinx.coroutines.launch

class DetailFragmentViewModel : ViewModel() {

   private val favouriteRepository = FavouriteRepository()
   var isSeeMore = true

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