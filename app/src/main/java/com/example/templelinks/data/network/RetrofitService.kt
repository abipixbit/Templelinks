package com.example.templelinks.data.network

import com.example.templelinks.BuildConfig
import com.example.templelinks.constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    fun retrofitService() : ApiInterface {

        val logging = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

       val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AuthorizationInterceptor())
            .build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiInterface::class.java)
    }

}