package com.example.templelinks.data.model.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TemplesResponse(

        @SerializedName("id")
        val id: Int?,

        @SerializedName("is_active")
        val isActive: Int?,

        @SerializedName("locale")
        val locale: Locale?,

        @SerializedName("temples")
        val temples: List<Temple>?,

        @SerializedName("updated_at")
        val updatedAt: String?,

        @SerializedName("view_type")
        val viewType: String?

    )
        data class Locale(

            @SerializedName("id")
            val id: Int?,

            @SerializedName("name")
            val name: String?,

        )

        @Parcelize
        data class Temple(

            @SerializedName("description")
            val description: String?,

            @SerializedName("is_favourite")
            var isFavourite : Boolean,

            @SerializedName("id")
            val id: Int?,

            @SerializedName("image_url")
            val imageUrl: String?,

            @SerializedName("is_puja_booking")
            val isPoojaBooking: Boolean?,

            @SerializedName("is_donation")
            val isDonation: Boolean?,

            @SerializedName("is_virtual_queue")
            val isVirtualQueue: Boolean?,

            @SerializedName("is_prasada")
            val isPrasada: Boolean?,

            @SerializedName("locale")
            val locale: TempleLocale?,

            @SerializedName("bank_charge")
            val bankingCharge : Double,

            @SerializedName("phone")
            val phoneNumber : String?

        ) : Parcelable

            @Parcelize
            data class TempleLocale(
                @SerializedName("address")
                val address: String?,

                @SerializedName("description")
                val description: String?,

                @SerializedName("id")
                val id: Int?,

                @SerializedName("name")
                val name: String?,

                @SerializedName("temple_id")
                val templeId: Int?,
            ) : Parcelable




