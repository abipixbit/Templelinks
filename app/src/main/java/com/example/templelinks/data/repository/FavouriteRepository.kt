package com.example.templelinks.data.repository

import android.util.Log
import com.example.templelinks.constants.TOKEN
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.network.RetrofitService

class FavouriteRepository {

    private val apiInterface = RetrofitService.retrofitService()
    private lateinit var favourite : ApiResponse<List<Temple>?>


    suspend fun setFavourite(id : Int?) {
        try {
          apiInterface.setFavourite(id!!)
        }
        catch (e : Exception) {
            Log.d("FavException", e.message.toString())
        }
    }

    suspend fun loadFavourite() : ApiResponse<List<Temple>?> {

        favourite = ApiResponse.loading()

        try {
            val response = apiInterface.loadFavourite()
            favourite = ApiResponse.success(response.data)
            }
        catch (e : Exception) {
            favourite = ApiResponse.error(data = null,e.message.toString())
        }
        return favourite
    }




}