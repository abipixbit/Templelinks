package com.example.templelinks.data.network

import com.example.templelinks.data.model.Banners
import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.response.DefaultResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET("deities")
    suspend fun loadDeities() : DefaultResponse<Deities>

    @GET("banners")
    suspend fun loadBanners() : DefaultResponse<Banners>

}