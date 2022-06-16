package com.example.templelinks.ui.fragment.pujabookingFragment.finalBookingFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.templelinks.R
import com.example.templelinks.data.model.Donations
import com.example.templelinks.data.model.FamilyMember
import com.example.templelinks.data.model.Payment
import com.example.templelinks.data.model.PujaPayment
import com.example.templelinks.databinding.FragmentFinalBokingBinding
import com.example.templelinks.ui.adapter.ConfrimPoojaAdapter
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.math.RoundingMode
import java.text.DecimalFormat


class FinalBokingFragment : Fragment() {

    private lateinit var binding : FragmentFinalBokingBinding
    private val arguments : FinalBokingFragmentArgs by navArgs()
    private lateinit var confirmPoojaAdapter : ConfrimPoojaAdapter
    private val viewModel : FinalBookingViewModel by viewModels()
    val famMembers  = mutableListOf<FamilyMember>()
    val pujaPayment = mutableListOf<PujaPayment>()
    val payment = mutableListOf<Payment>()



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
                val args = arguments.templeArgs

                viewModel.puja.forEach {
                    it.selectedFamilies?.forEach {
                        famMembers.add(FamilyMember (it.id, it.count))
                    }

                    pujaPayment.add(PujaPayment(it.translation.pujaId, it.price, famMembers, it.time))
                }

                payment.add(Payment(
                    args.id, arguments.selectedDate,
                    2.0,
                    pujaPayment,
                    Donations(viewModel.donationAmount.value),
                    args.gatewayCharge?.toDouble(),
                    args.gatewayCharge?.toDouble(),
                    args.gatewayCharge?.toDouble(),
                    args.gatewayCharge?.toDouble(),
                    args.isRazorpay.toString(),
                    viewModel.price.value,
                    viewModel.totalAmount.value
                ))

                Log.d("Payment", Gson().toJson(payment).toString())

            }

            btnDonation100.setOnClickListener {
                etDonation.setText(R.string._100_0)
            }

            btnDonation500.setOnClickListener {
                etDonation.setText(R.string._500_0)
            }

            btnDonation1000.setOnClickListener {
                etDonation.setText(R.string._1000)
            }

            etDonation.doOnTextChanged { text, start, before, count ->
                if (text.isNullOrEmpty()) {
                    etDonation.setText("0.0")
                    viewModel.findSum()
                }
                else {
                    viewModel.donationAmount.value = text.toString().toDouble()
                    viewModel.findSum()
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
                tvSubTotalAmount.text = getString(R.string.pooja_price_s, df.format(sum))
            }

            viewModel.totalAmount.observe(viewLifecycleOwner) { totalPrice ->
                tvTotalAmount.text = getString(R.string.pooja_price_s, df.format(totalPrice))
            }

            viewModel.donationAmount.observe(viewLifecycleOwner) { donationAmount ->
//                etDonation.setText(donationAmount.toString())
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
                    else -> {
                        btnDonation100.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.dark_grey)
                        btnDonation500.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.dark_grey)
                        btnDonation1000.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.dark_grey)
                    }

                }
            }
        }
    }
}