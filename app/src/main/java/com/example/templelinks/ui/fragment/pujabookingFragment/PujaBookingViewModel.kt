package com.example.templelinks.ui.fragment.pujabookingFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.Families
import com.example.templelinks.data.model.Pujas
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.repository.DeitiesRepository
import com.example.templelinks.data.repository.FamiliesRepository
import com.example.templelinks.data.repository.PujaRepository
import kotlinx.coroutines.launch
import java.util.*

class PujaBookingViewModel : ViewModel() {

    private val _deities = MutableLiveData<ApiResponse<List<Deities>?>>()
    val deities : LiveData<ApiResponse<List<Deities>?>>
        get() = _deities

    private val _pujas = MutableLiveData<ApiResponse<List<Pujas>?>>()
    val pujas : LiveData<ApiResponse<List<Pujas>?>>
        get() = _pujas

    private val _families = MutableLiveData<ApiResponse<List<Families>?>>()
    val families : LiveData<ApiResponse<List<Families>?>>
    get() = _families

    var pujaTempData : Map<Int?, List<Pujas>> = mutableMapOf()
    var selectedPooja = mutableListOf<Pujas>()

    val calendar: Calendar = Calendar.getInstance()

    init {
    loadFamilies()
    Log.d("PujaBookingViewModel", "View Model Created")
    }

    fun addPujaTempData(deityId : Int?, pujas : List<Pujas>) {
        pujaTempData = pujaTempData.plus(deityId to pujas)
        Log.d("tempData", pujaTempData.toString())
    }

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

    private fun loadFamilies() {
        viewModelScope.launch {
            _families.value = FamiliesRepository().loadFamilies()
        }
    }

    fun addSelectedPoojas(listPujas : MutableList<Pujas>) {
        selectedPooja = (selectedPooja + listPujas) as MutableList<Pujas>
        Log.d("selectedPooja", selectedPooja.toString())
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("PujaBookingViewModel", "View Model Cleared")
    }
}