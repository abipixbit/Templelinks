package com.example.templelinks.ui.fragment.detailfragment

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentDetailBinding
import com.example.templelinks.extensions.glide
import com.example.templelinks.extensions.navigation

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val arguments: DetailFragmentArgs by navArgs()

    private var isSeeMore = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayUI()

        binding.includeTempleLayout.tvTempleDescription.setOnClickListener {
            seeMore()
        }

        binding.toolBarTempleDetail.setNavigationOnClickListener {
            requireView().navigation(R.id.action_detailFragment_to_homeFragment)
        }

        requireView().glide(arguments.temples.imageUrl.toString(), binding.ivTempleDetail)

    }

    private fun seeMore() {
        if (isSeeMore) {
            binding.includeTempleLayout.tvTempleDescription.maxLines = 10

            isSeeMore = false
        }
        else {
            binding.includeTempleLayout.tvTempleDescription.maxLines = arguments.temples.locale?.description?.length!!

            isSeeMore = true
        }
    }

    private fun displayUI() {
        binding.toolBarTempleDetail.setNavigationIcon(R.drawable.ic_back)
        binding.includeTempleLayout.tvTemple.text = arguments.temples.locale?.name
        binding.includeTempleLayout.tvTempleDescription.text = arguments.temples.locale?.description
        binding.includeTempleLayout.includeAddress.tvTempleAddress.text = arguments.temples.locale?.address
        binding.includeTempleLayout.includePhone.tvTemplePhone.text = arguments.temples.phoneNumber
        binding.includeTempleLayout.tvTempleDescription.maxLines = 10

        buttonUI()

    }

    private fun buttonUI() {

        if (arguments.temples.isVirtualQueue == true) {
            binding.includeTempleLayout.layoutVirtualQueue.constraintLayoutButton.visibility = View.VISIBLE
            binding.includeTempleLayout.layoutVirtualQueue.tvItemName.text = getString(R.string.virtual_queue)
        }
        if (arguments.temples.isDonation == true) {
            binding.includeTempleLayout.layoutDonation.constraintLayoutButton.visibility = View.VISIBLE
            binding.includeTempleLayout.layoutDonation.tvItemName.text = getString(R.string.donation)
        }
        if (arguments.temples.isPoojaBooking == true) {
            binding.includeTempleLayout.layoutPoojaBooking.constraintLayoutButton.visibility = View.VISIBLE
            binding.includeTempleLayout.layoutPoojaBooking.tvItemName.text = getString(R.string.puja_booking)
        }
        if (arguments.temples.isPrasada == true) {
            binding.includeTempleLayout.layoutPrasadaBooking.constraintLayoutButton.visibility = View.VISIBLE
            binding.includeTempleLayout.layoutPrasadaBooking.tvItemName.text = getString(R.string.prasad)
        }

    }
}