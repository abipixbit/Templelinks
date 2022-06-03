package com.example.templelinks.data.model

import com.google.gson.annotations.SerializedName

data class Pujas
        (

        @SerializedName("deity_id")
        val deityId: Int,

        @SerializedName("temple_id")
        val temple_id: Int,

        @SerializedName("price")
        val price: Int,

        @SerializedName("translation")
        val translation: Translations
        )

        data class Translations(

        @SerializedName("puja_id")
        val pujaId: Int,

        @SerializedName("locale_id")
        val localeId: Int,

        @SerializedName("name")
        val pujaName: String,

        @SerializedName("description")
        val pujaDescription: String,

    )
