package com.example.templelinks.ui.fragment.pujabookingFragment.finalBookingFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.templelinks.data.model.Puja

class FinalBookingViewModel : ViewModel() {

    var puja = listOf<Puja>()
    var price = MutableLiveData(0)

    fun findSum() {
        price.value = 0
        val pujaAmount =  puja.map{ it.price }

      val familyCount = puja.map {
          it.selectedFamilies?.map { it.count }
      }

        for (i in 0 ..familyCount.size-1) {
                price.value = price.value?.plus(familyCount[i]?.sum()?.times(pujaAmount[i])!!)
        }

        Log.d("totalPrice", price.value.toString())

    }

}