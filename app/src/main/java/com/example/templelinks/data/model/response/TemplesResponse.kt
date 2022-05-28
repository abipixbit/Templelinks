package com.example.templelinks.data.model.response


import com.google.gson.annotations.SerializedName

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

        data class Temple(

            @SerializedName("description")
            val description: String?,

            @SerializedName("is_favourite")
            var isFavourite : Boolean,

            @SerializedName("devaswom")
            val devaswom: String?,

            @SerializedName("id")
            val id: Int?,

            @SerializedName("image_url")
            val imageUrl: String?,

            @SerializedName("locale")
            val locale: TempleLocale?,

        )
            data class TempleLocale(
                @SerializedName("address")
                val address: String?,

                @SerializedName("description")
                val description: String?,

                @SerializedName("id")
                val id: Int?,

                @SerializedName("locale_id")
                val localeId: Int?,

                @SerializedName("name")
                val name: String?,

                @SerializedName("temple_id")
                val templeId: Int?,
            )




