package com.example.templelinks.ui.fragment.favouriteFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.repository.FavouriteRepository
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {

    private val repository = FavouriteRepository()

    private val _favourite = MutableLiveData<ApiResponse<List<Temple>?>>()
    val favourite : LiveData<ApiResponse<List<Temple>?>> = _favourite

    init {
        loadFavourite()
        Log.d("FavViewModel","ViewModel Created")
    }


    fun loadFavourite()  {
        viewModelScope.launch {
            _favourite.postValue(ApiResponse.loading())
            _favourite.value = repository.loadFavourite()
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("FavViewModel", "ViewModel Destroyed")
    }
}