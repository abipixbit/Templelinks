package com.example.templelinks.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.templelinks.R
import com.example.templelinks.adapter.GodListAdapter
import com.example.templelinks.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gods = resources.getStringArray(R.array.gods_photo).toList()
        binding.rvGods.apply {
            adapter = GodListAdapter(gods,requireView())
        }

        imageSlider()
    }

    private fun imageSlider() {

        val bannerList = ArrayList<SlideModel>()

        bannerList.add(SlideModel("http://139.59.35.179:105/storage/banners/1625843112.png"))
        bannerList.add(SlideModel("http://139.59.35.179:105/storage/banners/1626433738.png"))
        bannerList.add(SlideModel("http://139.59.35.179:105/storage/banners/1625843112.png"))
        bannerList.add(SlideModel("http://139.59.35.179:105/storage/banners/1626433738.png"))
        bannerList.add(SlideModel("http://139.59.35.179:105/storage/banners/1625843112.png"))
        bannerList.add(SlideModel("http://139.59.35.179:105/storage/banners/1626433738.png"))

        binding.bannerLarge.ivSlider.setImageList(bannerList,ScaleTypes.FIT)
    }
}