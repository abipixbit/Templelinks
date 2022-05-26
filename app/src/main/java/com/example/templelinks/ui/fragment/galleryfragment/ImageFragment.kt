package com.example.templelinks.ui.fragment.galleryfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.templelinks.R
import com.example.templelinks.ui.adapter.GalleryButtonAdapter
import com.example.templelinks.ui.adapter.GalleryImageAdapter
import com.example.templelinks.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private lateinit var binding : FragmentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentImageBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gods = resources.getStringArray(R.array.gods_photo).toList()
        val buttonText = resources.getStringArray(R.array.button_text).toList()
        binding.rvImages.apply {
            adapter = GalleryImageAdapter(gods,view)
            layoutManager = GridLayoutManager(requireContext(),3,GridLayoutManager.VERTICAL,false)
        }

        binding.rvButton.adapter = GalleryButtonAdapter(buttonText)


    }
}