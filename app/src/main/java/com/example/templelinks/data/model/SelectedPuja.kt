package com.example.templelinks.data.model

data class SelectedPuja (
    var isSelected : Boolean = false,
    val pujaId : Int,
    val memberId : List<Int>
        )