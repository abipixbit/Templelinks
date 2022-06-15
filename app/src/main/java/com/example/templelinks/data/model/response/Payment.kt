package com.example.templelinks.data.model.response

data class Payment(
    val temple_id: Int,
    val date: String,
    val delivery_charge: Int,
    val pujas: List<Puja>,
    val donations: Donations,
    val address: Address,
    val gateway_charge_percentage: Int,
    val gateway_charge_amount: Int,
    val transfer_commission_percentage: Int,
    val transfer_commission_amount: Int,
    val gateway: String,
    val sub_total: Int,
    val total_amount: Int
) {
    data class Puja(
        val puja_id: Int,
        val price: Int,
        val family_members: List<FamilyMember>,
        val time: Int
    ) {
        data class FamilyMember(
            val member_id: Int,
            val count: Int
        )
    }

    data class Donations(
        val amount: Int
    )

    data class Address(
        val first_name: String,
        val last_name: String,
        val phone: String,
        val address_line_1: String,
        val address_line_2: String,
        val city: String,
        val pin: Int,
        val state: String,
        val is_primary: Boolean
    )
}