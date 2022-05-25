package com.example.templelinks.ui.fragment.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.model.Banners
import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.model.response.TemplesResponse
import com.example.templelinks.data.repository.BannerRepository
import com.example.templelinks.data.repository.DeitiesRepository
import com.example.templelinks.data.repository.HomeCategoryRepository
import com.example.templelinks.enums.ApiStatus
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    private val _deities = MutableLiveData<ApiResponse<List<Deities>?>>()
    val deities : LiveData<ApiResponse<List<Deities>?>>
    get() = _deities

    private val _banners = MutableLiveData<ApiResponse<List<Banners>?>>()
    val banners : LiveData<ApiResponse<List<Banners>?>>
    get() = _banners

    private val _homeCategory = MutableLiveData<ApiResponse<List<TemplesResponse>?>>()
    val homeCategory : LiveData<ApiResponse<List<TemplesResponse>?>>
    get() = _homeCategory

    init {
        loadDeities()
        loadHomeBanners()
        loadHomeCategory()
    }

    private fun loadHomeCategory() {
        viewModelScope.launch {
            _homeCategory.postValue(ApiResponse(ApiStatus.LOADING,null,null))
            _homeCategory.value = HomeCategoryRepository().loadHomeCategory()
        }
    }

    private fun loadDeities() {
        viewModelScope.launch {
            _deities.postValue(ApiResponse(ApiStatus.LOADING,null,null))
            _deities.value = DeitiesRepository().deities()
        }
    }

    private fun loadHomeBanners() {
        viewModelScope.launch {
            _banners.postValue(ApiResponse(ApiStatus.LOADING,null,null))
            _banners.value = BannerRepository().loadBanners()
        }
    }

}