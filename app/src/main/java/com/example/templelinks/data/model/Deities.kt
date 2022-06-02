package com.example.templelinks.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Deities
    (
    @SerializedName("id")
    val id: Int?,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("translation")
    val translation : Translation
    ) : Parcelable

@Parcelize
data class Translation
    (
    @SerializedName("name")
    val deitiesName : String?
    ) : Parcelable


