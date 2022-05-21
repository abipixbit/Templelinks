package com.example.templelinks.ui.fragment.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.model.Banners
import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.repository.BannerRepository
import com.example.templelinks.data.repository.DeitiesRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    private val _deities = MutableLiveData<ApiResponse<List<Deities>?>>()
    val deities : LiveData<ApiResponse<List<Deities>?>>
    get() = _deities

    private val _banners = MutableLiveData<ApiResponse<List<Banners>?>>()
    val banners : LiveData<ApiResponse<List<Banners>?>>
    get() = _banners

    init {
        loadDeities()
        loadBanners()
    }

    private fun loadDeities() {
        viewModelScope.launch {
            _deities.value = DeitiesRepository().deities()
        }
    }

    private fun loadBanners() {
        viewModelScope.launch {
            _banners.value = BannerRepository().loadBanners()
        }
    }

}