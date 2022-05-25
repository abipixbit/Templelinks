package com.example.templelinks.data.repository

import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.model.response.TemplesResponse
import com.example.templelinks.data.network.RetrofitService

class HomeCategoryRepository {

    private val apiInterface = RetrofitService.retrofitService()
    private lateinit var homeCategory : ApiResponse<List<TemplesResponse>?>


    suspend fun loadHomeCategory() : ApiResponse<List<TemplesResponse>?> {

        homeCategory = ApiResponse.loading()

        try {
            val response = apiInterface.loadHomeCategory()
            homeCategory = ApiResponse.success(response.data)
        }
        catch (e : Exception) {
            homeCategory = ApiResponse.error(data = null, message = e.message.toString())
        }
        return homeCategory
    }



}