package com.example.templelinks.ui.fragment.templeDetailFragment

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentDetailBinding
import com.example.templelinks.extensions.glide
import com.example.templelinks.extensions.navigation
import com.example.templelinks.extensions.toast

class TempleDetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val arguments: TempleDetailFragmentArgs by navArgs()
    private val viewModel  : DetailFragmentViewModel by viewModels()

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
        setupUI()

        binding.toolBarTempleDetail.apply {
            val temple = arguments.temple
            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.favouriteTemple -> {
                        if (temple.isFavourite) {
                            viewModel.deleteFavourite(temple.id)
                            temple.isFavourite = false
                            isFavourite(it)
                        }

                        else {
                            viewModel.setFavourite(temple.id)
                            temple.isFavourite = true
                            isFavourite(it)
                        }
                    }
                }
                true
            }

            setNavigationOnClickListener {
                requireView().navigation(R.id.action_detailFragment_to_homeFragment)
            }

        }

        binding.includeTempleLayout.apply {
            layoutPoojaBooking.cardLayoutButton.setOnClickListener {
                findNavController().navigate(TempleDetailFragmentDirections.actionDetailFragmentToPujaBookingFragment(arguments.temple))
            }

            includePhone.tvTemplePhone.apply {
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel: $text"))
                    startActivity(intent)
                }
            }
        }
    }

    private fun setupUI() {
       val temple = arguments.temple
        requireView().glide(temple.imageUrl.toString(), binding.ivTempleDetail)
        binding.toolBarTempleDetail.apply {
            setNavigationIcon(R.drawable.ic_back)
            isFavourite(this.menu.getItem(0))
        }

        binding.apply {
            includeTempleLayout.apply {
                layoutVirtualQueue.apply {
                    cardLayoutButton.isVisible = temple.isVirtualQueue == true
                    tvItemName.text = getString(R.string.virtual_queue) }

                layoutDonation.apply {
                    cardLayoutButton.isVisible = temple.isDonation == true
                    tvItemName.text = getString(R.string.donation)
                }
                layoutPoojaBooking.apply {
                    cardLayoutButton.isVisible = temple.isPoojaBooking == true
                    tvItemName.text = getString(R.string.pooja_booking)

                }
                layoutPrasadaBooking.apply {
                    cardLayoutButton.isVisible = temple.isPrasada == true
                    tvItemName.text = getString(R.string.prasad)
                }

                tvTempleDescription.setOnClickListener {

                    if (viewModel.isSeeMore) { tvTempleDescription.maxLines = 3
                        viewModel.isSeeMore = false }

                    else {
                        tvTempleDescription.maxLines = temple.locale?.description?.length!!
                        viewModel.isSeeMore = true
                    }
                }
                tvTemple.text = temple.locale?.name
                tvTempleDescription.text = temple.locale?.description
                includeAddress.tvTempleAddress.text = temple.locale?.address
                includePhone.tvTemplePhone.text = temple.phoneNumber
                tvTempleDescription.maxLines = 3
            }
        }
    }

    private fun isFavourite(menuItem : MenuItem) {
        menuItem.iconTintList = if (arguments.temple.isFavourite) {
             ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.app_color))
        }
        else {
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.unlike_color))
        }
    }

}