package com.example.templelinks.ui.fragment.pujabookingFragment.finalBookingFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.templelinks.data.model.Puja

class FinalBookingViewModel : ViewModel() {

    var puja = listOf<Puja>()
    var price = MutableLiveData(0.0)
    var totalAmount = MutableLiveData(0.0)
    var donationAmount  = MutableLiveData(0.0)

    fun findSum() {
        price.value = 0.0
        val pujaAmount =  puja.map{ it.price }

      val familyCount = puja.map {
          it.selectedFamilies?.map { it.count }
      }

        for (i in 0 ..familyCount.size-1) {
                price.value = pujaAmount[i]?.let { familyCount[i]?.sum()?.times(it)?.let { price.value?.plus(it) } }
        }

        Log.d("totalPrice", price.value.toString())

        findTotalAmount(18.0,donationAmount.value!!)
    }

    private fun findTotalAmount(gst : Double, donation : Double) {
        totalAmount.value = (price.value?.plus(donation))?.times(((gst.plus(100)).div(100)))
        Log.d("TotalAmount", this.totalAmount.value.toString())
    }

}