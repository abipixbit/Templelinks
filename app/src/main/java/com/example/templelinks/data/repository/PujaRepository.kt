package com.example.templelinks.data.repository

import com.example.templelinks.data.model.Banners
import com.example.templelinks.data.model.Pujas
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.network.ApiInterface
import com.example.templelinks.data.network.RetrofitService

class PujaRepository {

    private val apiInterface = RetrofitService.retrofitService()
    private lateinit var pujas : ApiResponse<List<Pujas>?>

    suspend fun loadPujas(templeId : Int?, deitiesId : Int?) : ApiResponse<List<Pujas>?> {

        try {
            val response = apiInterface.loadPujas(templeId, deitiesId)
            pujas = ApiResponse.success(response.data)
        }
        catch (e : Exception) {
            pujas = ApiResponse.error(data = null, message = e.message.toString())
        }
        return pujas
    }

}