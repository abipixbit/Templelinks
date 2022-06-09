package com.example.templelinks.ui.fragment.homeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.FavouriteEventBus
import com.example.templelinks.data.model.Banners
import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.model.response.TemplesResponse
import com.example.templelinks.data.repository.BannerRepository
import com.example.templelinks.data.repository.DeitiesRepository
import com.example.templelinks.data.repository.FavouriteRepository
import com.example.templelinks.data.repository.HomeCategoryRepository
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.enums.FavEvent
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {


    private val favouriteRepository = FavouriteRepository()
    private val deitiesRepository = DeitiesRepository()

    private val _deities = MutableLiveData<ApiResponse<List<Deities>?>>()
    val deities : LiveData<ApiResponse<List<Deities>?>> = _deities

    private val _banners = MutableLiveData<ApiResponse<List<Banners>?>>()
    val banners : LiveData<ApiResponse<List<Banners>?>> = _banners

    private val _homeCategory = MutableLiveData<ApiResponse<List<TemplesResponse>?>>()
    val homeCategory : LiveData<ApiResponse<List<TemplesResponse>?>> = _homeCategory

    private val _favourite = MutableLiveData<String>()
    val favourite : LiveData<String> = _favourite


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
            _deities.value = deitiesRepository.deities(null)
        }
    }

    private fun loadHomeBanners() {
        viewModelScope.launch {
            _banners.postValue(ApiResponse(ApiStatus.LOADING,null,null))
            _banners.value = BannerRepository().loadBanners()
        }
    }

    fun setFavourite (id : Int?) {
        viewModelScope.launch {
            _favourite.value = favouriteRepository.setFavourite(id)
        }
    }

    fun deleteFavourite(id : Int?) {
        viewModelScope.launch {
          _favourite.value =  favouriteRepository.deleteFavourite(id)
        }
    }

}