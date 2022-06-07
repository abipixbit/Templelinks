package com.example.templelinks.data.network

import com.example.templelinks.constants.TOKEN
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
        val requestBuilder = request.newBuilder()

//        if (TOKEN != null) {
//            requestBuilder.addHeader("Authorization", "Bearer $TOKEN")
//        }

        requestBuilder.addHeader("Authorization", "Bearer $TOKEN")
        requestBuilder.addHeader("Accept", "application/json")
//        requestBuilder.addHeader("Locale", "ml")
        return chain.proceed(requestBuilder.build())
    }
}