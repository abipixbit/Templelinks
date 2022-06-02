package com.example.templelinks.data.network

import com.example.templelinks.data.model.Banners
import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.response.DefaultResponse
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.model.response.TemplesResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("deities")
    suspend fun loadDeities() : DefaultResponse<Deities>

    @GET("banners")
    suspend fun loadBanners() : DefaultResponse<Banners>

    @GET("home-categories")
    suspend fun loadHomeCategory() : DefaultResponse<TemplesResponse>

    @GET("favourites-temples")
    suspend fun loadFavourite() : DefaultResponse<Temple>

    @POST("temples/{id}/add-to-favourite")
    suspend fun setFavourite(
        @Path("id") id : Int
    ) : Response<DefaultResponse<String>>

    @DELETE("temples/{id}/remove-from-favourite")
    suspend fun deleteFavourite(
        @Path("id") id : Int
    ) : Response<DefaultResponse<String>>

    @GET("temples")
    suspend fun loadDeitiesTemple(
    @Query("deity_id") deityId : Int?
    ) : DefaultResponse<Temple>
}