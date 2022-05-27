package com.example.templelinks.ui.fragment.homefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.setupWithNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.templelinks.R
import com.example.templelinks.data.repository.FavouriteRepository
import com.example.templelinks.ui.adapter.DeitiesListAdapter
import com.example.templelinks.ui.adapter.HomeCategoryListAdapter
import com.example.templelinks.databinding.FragmentHomeBinding
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.extensions.glide
import com.example.templelinks.extensions.navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()
    private val bannerList = ArrayList<SlideModel>()
    private lateinit var deitiesAdapter : DeitiesListAdapter
    private lateinit var homeCategoryAdapter : HomeCategoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container,false)
        binding.toolBarHome.inflateMenu(R.menu.menu_home)

        binding.toolBarHome.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.favourite -> requireView().navigation(R.id.action_homeFragment_to_favouriteFragment)
            }
            true
        }

        deitiesAdapter = DeitiesListAdapter()
        homeCategoryAdapter = HomeCategoryListAdapter()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            displayUI()

        binding.refreshLayout.setOnRefreshListener {
            displayUI()
            binding.refreshLayout.isRefreshing = false
        }

    }

    private fun displayUI() {
        loadDeities()
        loadHomeBanner()
        loadHomeCategory()
    }

    private fun loadHomeCategory() {
        viewModel.homeCategory.observe(viewLifecycleOwner) { apiresponse ->

//            binding.shimmerHomeCategory.isVisible = apiresponse.apiStatus == ApiStatus.LOADING

            when(apiresponse.apiStatus) {
                ApiStatus.SUCCESS -> {
                    binding.shimmerHomeCategory.visibility = View.INVISIBLE
                    binding.rvHomeCategory.adapter = homeCategoryAdapter
                    apiresponse.data.let {
                        homeCategoryAdapter.submitList(it)
                        Log.d("HomeFragHomeCateSuc", it.toString())
                    }
                }

                ApiStatus.ERROR -> apiresponse.message.let {
                    Log.d("HomeFragHomeCateFail", it.toString())
                }

                ApiStatus.LOADING -> {
                    binding.shimmerHomeCategory.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun loadHomeBanner() {
       viewModel.banners.observe(viewLifecycleOwner) { apiResponse ->

           when(apiResponse.apiStatus) {
               ApiStatus.SUCCESS -> {

                   binding.shimmerHomeBanner.visibility = View.INVISIBLE
                   binding.homeBanner.cardViewHomeBanner.visibility = View.VISIBLE
                   binding.krishnaGeethBanner.cardViewKrishnaGeethBanner.visibility = View.VISIBLE

                   apiResponse.data!!.let { banners ->
                       bannerList.clear()
                       for (i in banners)
                           bannerList.add(SlideModel(i.imageUrl.toString()))
                       binding.homeBanner.ivSlider.setImageList(bannerList, ScaleTypes.FIT)
                       requireView().glide(banners.get(0).imageUrl.toString(), binding.krishnaGeethBanner.krishnaGeethBanner)
                   }
               }
               ApiStatus.ERROR -> apiResponse.message.let {
                   Log.d("HomeFragBannerFail",it.toString())
               }

               ApiStatus.LOADING -> {
                   binding.shimmerHomeBanner.visibility = View.VISIBLE
                   binding.homeBanner.cardViewHomeBanner.visibility = View.INVISIBLE
                   binding.krishnaGeethBanner.cardViewKrishnaGeethBanner.visibility = View.INVISIBLE
               }
           }
       }
    }

    private fun loadDeities() {

        viewModel.deities.observe(viewLifecycleOwner) { apiResponse->

            when(apiResponse.apiStatus) {
                ApiStatus.SUCCESS -> {
                    binding.rvDeities.adapter = deitiesAdapter
                    binding.shimmerDeities.visibility = View.INVISIBLE
                    apiResponse.data.let { deities ->
                        deitiesAdapter.submitList(deities)
                        Log.d("HomeFragBannerSuc", deities.toString())
                    }
                }
                ApiStatus.ERROR -> apiResponse.message.let { message->
                    Log.d("HomeFragBannerFail", message.toString())
                }

                ApiStatus.LOADING -> {
                    binding.shimmerDeities.visibility = View.VISIBLE
                }
            }
        }
    }

}