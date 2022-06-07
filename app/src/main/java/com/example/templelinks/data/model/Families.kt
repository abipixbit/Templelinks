package com.example.templelinks.data.model

import com.google.gson.annotations.SerializedName

data class Families (
    @SerializedName("id")
    val id : Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("date_of_birth")
    val dateOfBirth : String,

    @SerializedName("phone")
    val phoneNumber : String,

    @SerializedName("nakshatra")
    val nakshathra : Nakshathra
    )

data class Nakshathra (
    @SerializedName("locale")
    val locale : NakshathraLocale
    )

data class NakshathraLocale(
    @SerializedName("name")
    val nakshathraName : String
)
