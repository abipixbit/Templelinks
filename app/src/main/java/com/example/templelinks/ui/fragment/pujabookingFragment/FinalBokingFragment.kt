package com.example.templelinks.ui.fragment.pujabookingFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
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
        confirmPoojaAdapter = ConfrimPoojaAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupUI()
        Log.d("Arguments", arguments.toString())
        binding.rvConfirmPooja.adapter = confirmPoojaAdapter
        confirmPoojaAdapter.submitList(arguments.confirmSelectedPoojaArgs?.toMutableList())
    }

    private fun setupUI() {
        binding.apply {
            toolBarFinalPujaBooking.toolBar.setupWithNavController(findNavController())
            tvTempleNameFinalBooking.text = arguments.templeName
            tvTempleAddressFinalBooking.text = arguments.templeAddress

        }
    }
}