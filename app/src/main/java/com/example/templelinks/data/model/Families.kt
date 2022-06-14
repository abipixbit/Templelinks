package com.example.templelinks.data.model

import android.os.CountDownTimer
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Families (

    var isSelected : Boolean? = false,

    var count: Int = 1,

    @SerializedName("id")
    val id : Int?,

    @SerializedName("name")
    val name : String?,

    @SerializedName("date_of_birth")
    val dateOfBirth : String?,

    @SerializedName("phone")
    val phoneNumber : String?,

    @SerializedName("nakshatra")
    val nakshathra : Nakshathra?
    ) : Parcelable

    @Parcelize
    data class Nakshathra (
    @SerializedName("locale")

    val locale : NakshathraLocale?
    ) : Parcelable

    @Parcelize
    data class NakshathraLocale(
    @SerializedName("name")

    val nakshathraName : String?
    ) : Parcelable
