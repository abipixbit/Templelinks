package com.example.templelinks.data.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Puja
        (

        var isSelected : Boolean = false,

        var selectedFamilies : List<Families>?,

        @SerializedName("deity_id")
        val deityId: Int,

        @SerializedName("temple_id")
        val temple_id: Int,

        @SerializedName("price")
        val price: Int,

        @SerializedName("translation")
        val translation: Translations
        ) : Parcelable

        @Parcelize
        data class Translations(

        @SerializedName("puja_id")
        val pujaId: Int,

        @SerializedName("locale_id")
        val localeId: Int,

        @SerializedName("name")
        val pujaName: String,

        @SerializedName("description")
        val pujaDescription: String,

    ) : Parcelable

