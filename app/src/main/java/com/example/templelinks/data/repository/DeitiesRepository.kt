package com.example.templelinks.data.repository

import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.network.RetrofitService

class DeitiesRepository {

    private val apiInterface = RetrofitService.retrofitService()
    private lateinit var deities : ApiResponse<List<Deities>?>

    private lateinit var deitiesTemple : ApiResponse<List<Temple>?>

    suspend fun deities(templeId : Int?) : ApiResponse<List<Deities>?> {

        deities = ApiResponse.loading()

        try {
            val response = apiInterface.loadDeities(templeId)
            deities = ApiResponse.success(response.data)
        }
        catch (e : Exception){
            deities = ApiResponse.error(data = null, message = e.message.toString())
        }

        return deities
    }


    suspend fun deitiesTemple(deityId : Int ?) : ApiResponse<List<Temple>?> {
        try {
            val response = apiInterface.loadDeitiesTemple(deityId)
            deitiesTemple = ApiResponse.success(response.data)
        }
        catch (e : Exception) {
            deitiesTemple = ApiResponse.error(data = null, message = e.message.toString())
        }
        return deitiesTemple
    }






}