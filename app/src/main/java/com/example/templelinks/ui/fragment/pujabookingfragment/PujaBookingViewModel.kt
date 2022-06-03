package com.example.templelinks.ui.fragment.pujabookingfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.Pujas
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.repository.DeitiesRepository
import com.example.templelinks.data.repository.PujaRepository
import com.example.templelinks.enums.ApiStatus
import kotlinx.coroutines.launch

class PujaBookingViewModel : ViewModel() {

    private val _deities = MutableLiveData<ApiResponse<List<Deities>?>>()
    val deities : LiveData<ApiResponse<List<Deities>?>>
        get() = _deities

    private val _pujas = MutableLiveData<ApiResponse<List<Pujas>?>>()
    val pujas : LiveData<ApiResponse<List<Pujas>?>>
        get() = _pujas


    fun loadDeities(templeId : Int?) {
        viewModelScope.launch {
            _deities.value = DeitiesRepository().deities(templeId)
        }
    }

    fun loadPujas(templeId : Int?, deitiesId : Int?) {
        viewModelScope.launch {
            _pujas.value = PujaRepository().loadPujas(templeId, deitiesId)
        }
    }

}