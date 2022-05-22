package com.example.templelinks.ui.fragment.homefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.templelinks.R
import com.example.templelinks.adapter.GodListAdapter
import com.example.templelinks.adapter.TempleMainAdapter
import com.example.templelinks.databinding.FragmentHomeBinding
import com.example.templelinks.enums.ApiStatus


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()
    private val bannerList = ArrayList<SlideModel>()

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

        displayUI()
        refresh()
    }

    private fun refresh() {
        binding.refreshLayout.setOnRefreshListener {
            displayUI()
            loadBanners()
            binding.refreshLayout.isRefreshing = false
        }

    }

    private fun displayUI() {
        loadDeities()
        loadBanners()
        loadHomeCategory()
    }


    private fun loadHomeCategory() {
        viewModel.homeCategory.observe(viewLifecycleOwner) { apiresponse ->
            when(apiresponse.apiStatus) {
                ApiStatus.SUCCESS -> apiresponse.data.let {
                    binding.rvTempleCategory.adapter = TempleMainAdapter(it!!)




                    Log.d("HomeFragHomeCateSuc",it.toString())
                }
                ApiStatus.ERROR -> apiresponse.message.let {
                    Log.d("HomeFragHomeCateFail",it.toString())
                }
            }
        }
    }

    private fun loadBanners() {
       viewModel.banners.observe(viewLifecycleOwner) { apiResponse ->
           when(apiResponse.apiStatus) {
               ApiStatus.SUCCESS -> apiResponse.data!!.let { banners->
                   bannerList.clear()
                   for (i in banners)
                       bannerList.add(SlideModel(i.imageUrl.toString()))
                   binding.bannerLarge.ivSlider.setImageList(bannerList,ScaleTypes.FIT)
               }
               ApiStatus.ERROR -> apiResponse.message.let {
                   Log.d("HomeFragBannerFail",it.toString())
               }
           }
       }
    }

    private fun loadDeities() {

        viewModel.deities.observe(viewLifecycleOwner) { apiResponse->
            when(apiResponse.apiStatus) {
                ApiStatus.SUCCESS -> apiResponse.data.let { deities->
                    binding.rvGods.adapter = GodListAdapter(deities!!)
                    Log.d("HomeFragBannerSuc",deities.toString())
                }
                ApiStatus.ERROR -> apiResponse.message.let { message->
                    Log.d("HomeFragBannerFail",message.toString())
                }
            }
        }
    }

}