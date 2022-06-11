package com.example.templelinks.ui.fragment.pujabookingFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.model.Families
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.repository.FamiliesRepository
import kotlinx.coroutines.launch

class FamilyMemberViewModel : ViewModel() {

    private val _families = MutableLiveData<ApiResponse<List<Families>?>>()
    val families : LiveData<ApiResponse<List<Families>?>>
        get() = _families

    init {
    loadFamilies()
    }

    private fun loadFamilies() {
        viewModelScope.launch {
            _families.value = ApiResponse.loading()
            _families.value = FamiliesRepository().loadFamilies()
        }
    }

}