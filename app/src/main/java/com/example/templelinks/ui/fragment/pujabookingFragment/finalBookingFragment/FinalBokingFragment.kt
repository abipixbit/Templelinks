package com.example.templelinks.ui.fragment.pujabookingFragment.finalBookingFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.templelinks.R
import com.example.templelinks.data.model.response.FamilyMember
import com.example.templelinks.databinding.FragmentFinalBokingBinding
import com.example.templelinks.ui.adapter.ConfrimPoojaAdapter
import java.math.RoundingMode
import java.text.DecimalFormat


class FinalBokingFragment : Fragment() {

    private lateinit var binding : FragmentFinalBokingBinding
    private val arguments : FinalBokingFragmentArgs by navArgs()
    private lateinit var confirmPoojaAdapter : ConfrimPoojaAdapter
    private val viewModel : FinalBookingViewModel by viewModels()
    private val finalPoojaMap = mutableMapOf<String, Any?>()
    private val familyMap = mutableMapOf<String, Any?>()
    private val pujaMap = mutableMapOf<String, Any?>()
    private val donationMap = mutableMapOf<String, Any?>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFinalBokingBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confirmPoojaAdapter = ConfrimPoojaAdapter { changedPuja ->
            viewModel.puja = changedPuja
            viewModel.findSum()
            Log.d("PujaChanged", viewModel.puja.toString())
        }
        setupUI()

        binding.btnConfirmBook.setOnClickListener {

            val puja = viewModel.puja

            puja.forEach {

                it.selectedFamilies?.forEach {
                    familyMap.put("member_id", it.id)
                    familyMap.put("count", it.count)
                }

                puja.forEach {
                    pujaMap.apply {
                        put("puja_id", it.translation.pujaId)
                        put("price", it.price)
                        put("family_members", listOf(familyMap))
                        put("time", it.time)
                    }
                }

                donationMap.put("amount", 25)


            finalPoojaMap.apply {
                put("temple_id", arguments.templeArgs.id)
                put("date", arguments.selectedDate)
                put("delivery_charge", 2)
                put("pujas", listOf(pujaMap))
                put("donations", donationMap)
                put("gateway_charge_percentage", arguments.templeArgs.gatewayCharge)
                put("gateway_charge_amount", arguments.templeArgs.gatewayCharge)
                put("transfer_commission_percentage", arguments.templeArgs.gatewayCharge)
                put("transfer_commission_amount", arguments.templeArgs.gatewayCharge)
                put("gateway", arguments.templeArgs.isRazorpay)
                put("sub_total", viewModel.price.value)
                put("total_amount", viewModel.totalAmount.value)
            }







            }



            Log.d("Map", finalPoojaMap.toString())


        }


    }

    private fun setupUI() {
        Log.d("Arguments", arguments.toString())
        val args = arguments.templeArgs
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN

        binding.apply {
            toolBarFinalPujaBooking.toolBar.setupWithNavController(findNavController())

            tvTempleNameFinalBooking.text = args.locale?.name
            tvTempleAddressFinalBooking.text = args.locale?.address

            tvBankingAmount.text = getString(R.string.pooja_price_float, df.format(args.bankingCharge))
            tvGSTAmount.text = getString(R.string.pooja_price_float, df.format(18.00))
            rvConfirmPooja.adapter = confirmPoojaAdapter
            confirmPoojaAdapter.submitList(arguments.confirmSelectedPoojaArgs?.toMutableList())

            viewModel.price.observe(viewLifecycleOwner) { sum ->
                tvSubTotalAmount.text = getString(R.string.pooja_price, sum)
            }

            viewModel.totalAmount.observe(viewLifecycleOwner) { totalPrice ->
                tvTotalAmount.text = getString(R.string.pooja_price_float, df.format(totalPrice))
            }
        }
    }
}