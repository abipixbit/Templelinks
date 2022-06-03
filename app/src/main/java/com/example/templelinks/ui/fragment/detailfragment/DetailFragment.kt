package com.example.templelinks.ui.fragment.detailfragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentDetailBinding
import com.example.templelinks.extensions.glide
import com.example.templelinks.extensions.navigation
import com.example.templelinks.extensions.toast

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val arguments: DetailFragmentArgs by navArgs()
    private val viewModel  : DetailFragmentViewModel by viewModels()
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

        binding.includeTempleLayout.layoutPoojaBooking.cardLayoutButton.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(DetailFragmentDirections.actionDetailFragmentToPujaBookingFragment(arguments.temples))
        }

        binding.ivFavourite.setOnClickListener {

            requireContext().toast("Clicked")

            if (arguments.temples.isFavourite) {
                viewModel.deleteFavourite(arguments.temples.id)
                arguments.temples.isFavourite = false
                likeButtonColor()
            }

            else {
                viewModel.setFavourite(arguments.temples.id)
                arguments.temples.isFavourite = true
                likeButtonColor()
            }
        }
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
        requireView().glide(arguments.temples.imageUrl.toString(), binding.ivTempleDetail)

        likeButtonColor()

        buttonUI()

    }

    private fun likeButtonColor() {
        if (arguments.temples.isFavourite) {
            binding.ivFavourite.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
        }
        else {
            binding.ivFavourite.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.unlike_color))
        }
    }

    private fun buttonUI() {

        if (arguments.temples.isVirtualQueue == true) {
            binding.includeTempleLayout.layoutVirtualQueue.cardLayoutButton.visibility = View.VISIBLE
            binding.includeTempleLayout.layoutVirtualQueue.tvItemName.text = getString(R.string.virtual_queue)
        }
        if (arguments.temples.isDonation == true) {
            binding.includeTempleLayout.layoutDonation.cardLayoutButton.visibility = View.VISIBLE
            binding.includeTempleLayout.layoutDonation.tvItemName.text = getString(R.string.donation)
        }
        if (arguments.temples.isPoojaBooking == true) {
            binding.includeTempleLayout.layoutPoojaBooking.cardLayoutButton.visibility = View.VISIBLE
            binding.includeTempleLayout.layoutPoojaBooking.tvItemName.text = getString(R.string.puja_booking)
        }
        if (arguments.temples.isPrasada == true) {
            binding.includeTempleLayout.layoutPrasadaBooking.cardLayoutButton.visibility = View.VISIBLE
            binding.includeTempleLayout.layoutPrasadaBooking.tvItemName.text = getString(R.string.prasad)
        }

    }
}