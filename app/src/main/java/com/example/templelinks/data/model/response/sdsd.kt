package com.example.templelinks.data.model.response

    data class Data
        (
        val id: Int,
        val view_type: String,
        val is_active: Int,
        val locale: Locale,
        val temples: List<Temple>
        )

        data class Locale
        (
            val id: Int,
            val name: String,
        )

        data class Temple
        (
            val id: Int,
            val phone: String,
            val default_deity_id: Int,
            val is_puja_booking: Boolean,
            val is_donation: Boolean,
            val is_virtual_queue: Boolean,
            val is_prasada: Boolean,
            val puja_prasad_delivery: Int,
            val sub_account: Any,
            val image_url: String,
            val is_favourite: Boolean,
            val locale: TempleLocale,
            val title: String,
            val description: String,
            val url: String,
        )


            data class TempleLocale
            (
                val id: Int,
                val temple_id: Int,
                val locale_id: Int,
                val name: String,
                val description: String,
                val address: String,
                val created_at: String,
                val updated_at: String,
                val deleted_at: Any
            )


