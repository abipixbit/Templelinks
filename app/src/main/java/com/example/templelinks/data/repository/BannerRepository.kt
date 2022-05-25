package com.example.templelinks.data.repository

import com.example.templelinks.data.model.Banners
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.network.RetrofitService

class BannerRepository {


    private val apiResponse = RetrofitService.retrofitService()
    private lateinit var banners : ApiResponse<List<Banners>?>

    suspend fun loadBanners() : ApiResponse<List<Banners>?> {

        banners = ApiResponse.loading()

        try {
            val response = apiResponse.loadBanners()
            banners = ApiResponse.success(response.data)
        }
        catch (e : Exception) {
            banners = ApiResponse.error(data = null, e.message.toString())
        }
        return banners
    }




}