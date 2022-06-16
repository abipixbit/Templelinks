package com.example.templelinks.ui.fragment.pujabookingFragment.finalBookingFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentFinalBokingBinding
import com.example.templelinks.ui.adapter.ConfrimPoojaAdapter
import com.google.gson.JsonParser
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

        binding.apply {

            btnConfirmBook.setOnClickListener {
                val puja = viewModel.puja
                val args = arguments.templeArgs


                puja.forEach {
                    it.selectedFamilies?.forEach {
                        familyMap["member_id"] = it.id
                        familyMap["count"] = it.count
                    }

                    pujaMap["puja_id"] = it.translation.pujaId
                    pujaMap["price"] = it.price
                    pujaMap["family_members"] = listOf(familyMap)
                    pujaMap["time"] = it.time
                }
                donationMap["amount"] = 25

                finalPoojaMap["temple_id"] = args.id
                finalPoojaMap["date"] = arguments.selectedDate
                finalPoojaMap["delivery_charge"] = 2
                finalPoojaMap["pujas"] = listOf(pujaMap)
                finalPoojaMap["donations"] = donationMap
                finalPoojaMap["gateway_charge_percentage"] = args.gatewayCharge
                finalPoojaMap["gateway_charge_amount"] = args.gatewayCharge
                finalPoojaMap["transfer_commission_percentage"] = args.gatewayCharge
                finalPoojaMap["transfer_commission_amount"] = args.gatewayCharge
                finalPoojaMap["gateway"] = args.isRazorpay
                finalPoojaMap["sub_total"] = viewModel.price.value
                finalPoojaMap["total_amount"] = viewModel.totalAmount.value

                Log.d("Map", JsonParser().parse(finalPoojaMap.toString()).toString())
            }

            btnDonation100.setOnClickListener {
                viewModel.donationAmount.value = 100.0
                viewModel.findSum()
            }

            btnDonation500.setOnClickListener {
                viewModel.donationAmount.value = 500.0
                viewModel.findSum()
            }

            btnDonation1000.setOnClickListener {
                viewModel.donationAmount.value = 1000.0
                viewModel.findSum()
            }

            viewModel.donationAmount.observe(viewLifecycleOwner) { donationAmount ->
                        etDonation.setText(donationAmount.toString())
                        tvDonationAmount.text = getString(R.string.pooja_price_s, donationAmount.toString())

                when (donationAmount) {
                    100.0 -> {
                        btnDonation100.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.app_color)
                        btnDonation500.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.dark_grey)
                        btnDonation1000.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.dark_grey)
                    }
                    500.0 -> {
                        btnDonation100.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.dark_grey)
                        btnDonation500.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.app_color)
                        btnDonation1000.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.dark_grey)
                    }
                    1000.0 -> {
                        btnDonation100.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.dark_grey)
                        btnDonation500.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.dark_grey)
                        btnDonation1000.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.app_color)
                    }

                }
            }
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

            tvGSTAmount.text = getString(R.string.gst_percentage, df.format(18.0))
            rvConfirmPooja.adapter = confirmPoojaAdapter
            confirmPoojaAdapter.submitList(arguments.confirmSelectedPoojaArgs?.toMutableList())

            viewModel.price.observe(viewLifecycleOwner) { sum ->
                tvSubTotalAmount.text = getString(R.string.pooja_price, sum)
            }

            viewModel.totalAmount.observe(viewLifecycleOwner) { totalPrice ->
                tvTotalAmount.text = getString(R.string.pooja_price_s, df.format(totalPrice))
            }
        }
    }
}