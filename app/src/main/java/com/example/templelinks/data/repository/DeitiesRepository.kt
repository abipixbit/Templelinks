package com.example.templelinks.data.repository

import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.network.RetrofitService

class DeitiesRepository {

    private val apiInterface = RetrofitService.retrofitService()
    private lateinit var deities : ApiResponse<List<Deities>?>

    suspend fun deities() : ApiResponse<List<Deities>?> {

        deities = ApiResponse.loading()

        try {
            val response = apiInterface.loadDeities()
            deities = ApiResponse.success(response.data)
        }
        catch (e : Exception){
            deities = ApiResponse.error(data = null, message = e.message.toString())
        }

        return deities
    }



}