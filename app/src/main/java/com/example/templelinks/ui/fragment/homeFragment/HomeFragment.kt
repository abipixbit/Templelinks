package com.example.templelinks.ui.fragment.homeFragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.templelinks.R
import com.example.templelinks.data.model.Banners
import com.example.templelinks.databinding.FragmentHomeBinding
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.extensions.glide
import com.example.templelinks.extensions.navigation
import com.example.templelinks.extensions.snackBarLike
import com.example.templelinks.ui.adapter.DeitiesListAdapter
import com.example.templelinks.ui.adapter.HomeBannerAdapter
import com.example.templelinks.ui.adapter.HomeCategoryListAdapter
import org.greenrobot.eventbus.EventBus
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()
    private var bannerSize = 0
    private lateinit var deitiesAdapter : DeitiesListAdapter
    private lateinit var homeCategoryAdapter : HomeCategoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container,false)

        deitiesAdapter = DeitiesListAdapter { deities ->
            Navigation.findNavController(requireView()).navigate(HomeFragmentDirections.actionHomeFragmentToDeitiesFragment(deities))
       }


        homeCategoryAdapter = HomeCategoryListAdapter ({currentTempleList ->

            if (currentTempleList[0].isFavourite) {
                viewModel.deleteFavourite(currentTempleList[0].id)
                currentTempleList[0].isFavourite = false

            } else {
                viewModel.setFavourite(currentTempleList[0].id)
                currentTempleList[0].isFavourite = true
            }

        }, { currentTemple ->
            Navigation.findNavController(requireView()).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(currentTemple))
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBarHome.apply {
            inflateMenu(R.menu.menu_home)
            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.favourite -> requireView().navigation(R.id.action_homeFragment_to_favouriteFragment) }
                true
            }
        }


        setupUI()

        binding.refreshLayout.setOnRefreshListener {
            setupUI()
            binding.refreshLayout.isRefreshing = false
        }

        val handler = Handler(Looper.getMainLooper())
        val timer = Timer()
        val runnable = Runnable {

            var currentPage = binding.homeBanner.viewPagerHomeBanner.currentItem
            if (currentPage == bannerSize-1) { currentPage = -1 }
            binding.homeBanner.viewPagerHomeBanner.setCurrentItem(1+currentPage, true)
        }
        timer.schedule(object : TimerTask(){
            override fun run() {
                handler.post(runnable)
            }
        },2000,3000)

    }

    private fun setupUI() {
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

                   binding.apply {
                       homeBanner.cardViewHomeBanner.visibility = View.VISIBLE
                       shimmerHomeBanner.visibility = View.INVISIBLE
                       krishnaGeethBanner.cardViewKrishnaGeethBanner.visibility = View.VISIBLE

                       apiResponse.data!!.let { banners ->
                           bannerSize = banners.size
                           Log.d("bannerSize",banners.size.toString())
                           homeBanner.viewPagerHomeBanner.adapter = HomeBannerAdapter(banners)
                           requireView().glide(banners.get(0).imageUrl.toString(), binding.krishnaGeethBanner.krishnaGeethBanner)
                       }
                   }
               }

               ApiStatus.ERROR -> apiResponse.message.let {
                   Log.d("HomeFragBannerFail",it.toString())
               }

               ApiStatus.LOADING -> {
                   binding.apply {
                       shimmerHomeBanner.visibility = View.VISIBLE
                       krishnaGeethBanner.cardViewKrishnaGeethBanner.visibility = View.INVISIBLE
                       homeBanner.cardViewHomeBanner.visibility = View.INVISIBLE }
               }
           }
       }
    }

    private fun loadDeities() {

        viewModel.deities.observe(viewLifecycleOwner) { apiResponse->

            when(apiResponse.apiStatus) {
                ApiStatus.SUCCESS -> {
                    binding.apply {
                        rvDeities.adapter = deitiesAdapter
                        shimmerDeities.visibility = View.INVISIBLE
                    }
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