package com.example.templelinks.ui.fragment.deitiesFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.templelinks.databinding.FragmentDeitiesBinding
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.ui.adapter.FavouriteAdapter

class DeitiesFragment : Fragment() {

    private lateinit var binding : FragmentDeitiesBinding
    lateinit var deitiesAdapter : FavouriteAdapter
    private val viewModel : DeitiesViewModel by viewModels()
    private val arguments: DeitiesFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDeitiesBinding.inflate(layoutInflater, container, false)

        deitiesAdapter = FavouriteAdapter { currentTemple ->
            Navigation.findNavController(requireView()).navigate(DeitiesFragmentDirections.actionDeitiesFragmentToDetailFragment(currentTemple))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            toolBarDeitiesTemple.tvToolBar.text = arguments.deitiesArguments.translation.deitiesName
            rvDeitiesTemples.adapter = deitiesAdapter
        }
        viewModel.loadDeitiesTemple(arguments.deitiesArguments.id)
        loadDeitiesTemple()
    }

    private fun loadDeitiesTemple() {
        viewModel.deitiesTemple.observe(viewLifecycleOwner) { apiRespone ->
            when (apiRespone.apiStatus) {
                ApiStatus.SUCCESS -> {
                    apiRespone.data.let { deitiesTemple ->
                        deitiesAdapter.submitList(deitiesTemple)
                    }
                }
                ApiStatus.ERROR -> apiRespone.message.let { message ->
                    Log.d("DeitiesErrorMessage", message.toString())
                }
                else -> {}
            }
        }
    }

}