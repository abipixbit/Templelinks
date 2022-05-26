package com.example.templelinks.ui.fragment.galleryfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentGalleryBinding
import com.example.templelinks.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class GalleryFragment : Fragment() {

    private lateinit var binding : FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(layoutInflater,container,false)
        binding.toolBarGallery.tvToolBar.text = resources.getString(R.string.gallery)

        val tabName = arrayOf("Images","Audios")

        binding.viewPager2.adapter = ViewPagerAdapter(childFragmentManager,lifecycle)
        TabLayoutMediator(binding.tabLayout,binding.viewPager2) { tab, position ->
            tab.text = tabName[position]
        } . attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}