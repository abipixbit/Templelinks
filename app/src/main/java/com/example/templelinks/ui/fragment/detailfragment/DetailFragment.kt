package com.example.templelinks.ui.fragment.detailfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentDetailBinding
import com.example.templelinks.extensions.glide

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val arguments: DetailFragmentArgs by navArgs()

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
        var isCheck = true

        binding.includeTempleLayout.tvTemple.text = arguments.temples.locale?.name
        binding.includeTempleLayout.tvTempleDescription.text = arguments.temples.locale?.description
        binding.includeTempleLayout.includeAddress.tvTempleAddress.text = arguments.temples.locale?.address
        binding.includeTempleLayout.includePhone.tvTemplePhone.text = arguments.temples.phoneNumber

        binding.includeTempleLayout.tvTempleDescription.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (isCheck) {
                    binding.includeTempleLayout.tvTempleDescription.maxLines = 10
                    isCheck = false
                }
                else {
                    binding.includeTempleLayout.tvTempleDescription.maxLines = 20
                    isCheck = true
                }
            }
        })

        requireView().glide(arguments.temples.imageUrl.toString(), binding.ivTempleDetail)

    }
}