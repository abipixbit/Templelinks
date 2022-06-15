package com.example.templelinks.data.model.response


    data class PaymentPuja(
        var puja_id: Int?,
        var price: Int?,
        val family_members: List<FamilyMember>,
        val time: Int?
    )

    data class FamilyMember(
        val member_id: Int?,
        val count: Int?
    )

