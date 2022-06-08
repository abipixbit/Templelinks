package com.example.templelinks.ui.fragment.pujabookingfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentFinalBokingBinding
import com.example.templelinks.ui.adapter.ConfrimPoojaAdapter


class FinalBokingFragment : Fragment() {

    private lateinit var binding : FragmentFinalBokingBinding
    private val arguments : FinalBokingFragmentArgs by navArgs()
    private lateinit var confirmPoojaAdapter : ConfrimPoojaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFinalBokingBinding.inflate(layoutInflater, container, false)
        updateUI()
        confirmPoojaAdapter = ConfrimPoojaAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvConfirmPooja.adapter = confirmPoojaAdapter
        confirmPoojaAdapter.submitList(mutableListOf(arguments.confirmSelectedPoojaArgs))

    }

    private fun updateUI() {
        binding.tvTempleNameFinalBooking.text = arguments.templeName
        binding.tvTempleAddressFinalBooking.text = arguments.templeAddress
        binding.toolBarFinalPujaBooking.tvToolBar.text = getString(R.string.pooja_booking)
    }
}