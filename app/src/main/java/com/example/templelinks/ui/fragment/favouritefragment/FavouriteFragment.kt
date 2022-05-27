package com.example.templelinks.ui.fragment.favouritefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentFavouriteBinding
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.extensions.navigation
import com.example.templelinks.ui.adapter.FavouriteAdapter

class FavouriteFragment : Fragment() {

    private lateinit var binding : FragmentFavouriteBinding
    private val viewModel : FavouriteViewModel by viewModels()
    lateinit var favourite : FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(layoutInflater, container,false)
        binding.toolBarFavourite.tvToolBar.text = getString(R.string.favourite)
        binding.toolBarFavourite.toolBar.setNavigationIcon(R.drawable.ic_back)
        favourite = FavouriteAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBarFavourite.toolBar.setNavigationOnClickListener {
            it.navigation(R.id.action_favouriteFragment_to_homeFragment)
        }

        updateUI()

        binding.refreshFavourite.setOnRefreshListener {
            viewModel.loadFavourite()
            updateUI()
            binding.refreshFavourite.isRefreshing = false
        }
    }

    private fun updateUI() {
        viewModel.favourite.observe(viewLifecycleOwner) { apiResponse ->
            when(apiResponse.apiStatus) {
                ApiStatus.SUCCESS -> {
                    if (apiResponse.data?.size == 0) {
                        binding.tvFav.visibility = View.VISIBLE
                        binding.rvFavourite.adapter = favourite
                    }
                    else {
                        binding.rvFavourite.adapter = favourite
                        apiResponse.data.let { temple ->
                            binding.tvFav.visibility = View.GONE
                            favourite.submitList(temple)
                        }
                    }
                }
                ApiStatus.ERROR -> Log.d("FavError", apiResponse.message.toString())
                else -> {}
            }
        }
    }
}