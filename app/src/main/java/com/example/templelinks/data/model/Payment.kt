package com.example.templelinks.data.model

data class Payment(
    val temple_id: Int?,
    val date: String?,
    val delivery_charge: Double?,
    val pujas: List<PujaPayment?>,
    val donations: Donations?,
    val gateway_charge_percentage: Double?,
    val gateway_charge_amount: Double?,
    val transfer_commission_percentage: Double?,
    val transfer_commission_amount: Double?,
    val gateway: String?,
    val sub_total: Double?,
    val total_amount: Double?
    )
    data class PujaPayment(
        val puja_id: Int?,
        val price: Int?,
        val family_members: List<FamilyMember?>,
        val time: Int?
    )
        data class FamilyMember(
            val member_id: Int?,
            val count: Int?
        )


    data class Donations(
        val amount: Double?
    )
