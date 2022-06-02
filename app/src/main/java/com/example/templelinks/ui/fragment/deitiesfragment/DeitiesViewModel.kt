package com.example.templelinks.ui.fragment.deitiesfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.repository.DeitiesRepository
import kotlinx.coroutines.launch

class DeitiesViewModel : ViewModel() {

    private val deitiesRepository = DeitiesRepository()

    private val _deitiesTemple = MutableLiveData<ApiResponse<List<Temple>?>>()
    val deitiesTemple : LiveData<ApiResponse<List<Temple>?>>
        get() = _deitiesTemple

    fun loadDeitiesTemple(deitiesID : Int?) {
        viewModelScope.launch {
            _deitiesTemple.value = deitiesRepository.deitiesTemple(deitiesID)
        }
    }
}