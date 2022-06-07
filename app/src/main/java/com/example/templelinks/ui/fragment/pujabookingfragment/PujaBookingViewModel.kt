package com.example.templelinks.ui.fragment.pujabookingfragment

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
import com.example.templelinks.enums.ApiStatus
import kotlinx.coroutines.launch

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

    var familyMember = mutableListOf<Int>()
    var pujaBook : Map<Int, List<Int>> = mutableMapOf()
    var check = false

    init {
    loadFamilies()
    Log.d("PujaBookingViewModel", "View Model Created")
    }

    fun pujaBook(pujaId : Int, listFamilyId : List<Int>) {
        pujaBook = pujaBook.plus( pujaId to listFamilyId)
        Log.d("PujaMap", pujaBook.toString())
    }

    fun addFamMember(famId : Int) {
        familyMember.clear()
        if (!familyMember.contains(famId))
        familyMember.add(famId)
        Log.d("FamilyId",familyMember.toString())
    }

    fun removeFamMember(famId : Int) {
        familyMember.removeAll(listOf(famId))
        Log.d("FamilyIdRemove",familyMember.toString())
    }

    fun removeFam() {
        familyMember.clear()
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

    override fun onCleared() {
        super.onCleared()
        Log.d("PujaBookingViewModel", "View Model Cleared")
    }
}