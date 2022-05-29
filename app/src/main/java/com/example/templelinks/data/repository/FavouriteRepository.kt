package com.example.templelinks.data.repository

import android.util.Log
import com.example.templelinks.constants.TOKEN
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.network.RetrofitService

class FavouriteRepository {

    private val apiInterface = RetrofitService.retrofitService()
    private lateinit var favourite : ApiResponse<List<Temple>?>

    private var message = ""


    suspend fun setFavourite(id : Int?) : String {
        try {
            if (id != null) {
               val response =  apiInterface.setFavourite(id)
                Log.d("FavMessage", response.raw().toString())
                message = response.body()?.message.toString()
            }
        }
        catch (e : Exception) {
            message = e.message.toString()
            Log.d("FavException", e.message.toString())
        }

        return message
    }

    suspend fun loadFavourite() : ApiResponse<List<Temple>?> {

        favourite = ApiResponse.loading()

        try {
            val response = apiInterface.loadFavourite()
            favourite = ApiResponse.success(response.data)
            }
        catch (e : Exception) {
            favourite = ApiResponse.error(data = null, e.message.toString())
        }
        return favourite
    }

    suspend fun deleteFavourite(id : Int ?) : String {
        try {
            if (id != null) {
               val response =  apiInterface.deleteFavourite(id)
                message = response.body()?.message.toString()

            }
        } catch (e : Exception) {
            Log.d("FavDeleteExc", e.message.toString())
        }

        return message
    }




}