package com.example.templelinks.ui.fragment.pujabookingFragment.finalBookingFragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentFinalBokingBinding
import com.example.templelinks.ui.adapter.ConfrimPoojaAdapter
import java.math.RoundingMode
import java.text.DecimalFormat


class FinalBokingFragment : Fragment() {

    private lateinit var binding : FragmentFinalBokingBinding
    private val arguments : FinalBokingFragmentArgs by navArgs()
    private lateinit var confirmPoojaAdapter : ConfrimPoojaAdapter
    private val viewModel : FinalBookingViewModel by viewModels()



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