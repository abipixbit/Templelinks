package com.example.templelinks.data.repository

import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.Families
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.network.RetrofitService

class FamiliesRepository {
    val apiResponse = RetrofitService.retrofitService()
    private lateinit var families : ApiResponse<List<Families>?>

    suspend fun loadFamilies() : ApiResponse<List<Families>?> {

        try {
            val response = apiResponse.loadFamilies()
            families = ApiResponse.success(response.data)
        }
        catch (e : Exception) {
            families = ApiResponse.error(null, e.message.toString())
        }

        return families

    }


}