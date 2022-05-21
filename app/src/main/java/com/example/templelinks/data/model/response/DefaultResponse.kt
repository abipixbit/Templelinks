package com.example.templelinks.data.model.response

import com.google.gson.annotations.SerializedName

data class DefaultResponse<T>
    (
        @SerializedName("message")
        val message : String,

        @SerializedName("data")
        val data : List<T>
    )