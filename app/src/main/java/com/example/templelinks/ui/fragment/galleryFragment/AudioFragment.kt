package com.example.templelinks.ui.fragment.galleryFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.templelinks.databinding.FragmentAudioBinding


class AudioFragment : Fragment() {

    private lateinit var binding : FragmentAudioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAudioBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

}