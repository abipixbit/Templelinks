package com.example.templelinks.ui.fragment.pujabookingFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.Puja
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.repository.DeitiesRepository
import com.example.templelinks.data.repository.PujaRepository
import kotlinx.coroutines.launch
import java.util.*

class PujaBookingViewModel : ViewModel() {

    private val _deities = MutableLiveData<ApiResponse<List<Deities>?>>()
    val deities : LiveData<ApiResponse<List<Deities>?>>
        get() = _deities

    private val _pujas = MutableLiveData<ApiResponse<List<Puja>?>>()
    val pujas : LiveData<ApiResponse<List<Puja>?>>
        get() = _pujas

    var selectedPoojas = mutableListOf<Puja>()

    val calendar: Calendar = Calendar.getInstance()

    init {
    Log.d("PujaBookingViewModel", "View Model Created")
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

    fun addSelectedPoojas(listPujas : MutableList<Puja>) {
        if(!selectedPoojas.containsAll(listPujas))
        selectedPoojas = (selectedPoojas + listPujas) as MutableList<Puja>
        Log.d("selectedPooja", selectedPoojas.toString())
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("PujaBookingViewModel", "View Model Cleared")
    }
}