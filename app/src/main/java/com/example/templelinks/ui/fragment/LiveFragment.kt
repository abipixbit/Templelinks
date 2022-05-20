package com.example.templelinks.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.templelinks.R
import com.example.templelinks.adapter.LiveStreamAdapter
import com.example.templelinks.data.LiveListData
import com.example.templelinks.databinding.FragmentLiveBinding

class LiveFragment : Fragment() {

    private lateinit var binding : FragmentLiveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLiveBinding.inflate(layoutInflater,container,false)
        binding.toolBarLive.tvToolBar.text = resources.getString(R.string.live_stream)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val liveList = ArrayList<LiveListData>()

        liveList.add(LiveListData("https://cdn.wallpapersafari.com/34/22/YZ3pfP.jpg","Buddha Jayanthi"))
        liveList.add(LiveListData("https://1.bp.blogspot.com/-HWEutzqOaYg/Xy52fakClDI/AAAAAAAAGck/fjAc93z2m1ATUYloVuQxedPB4NLafbESgCLcBGAsYHQ/s1600/lord-ganesha-hindu-gods-wallpaper-picture-images-background-stock-photos-divyatattva-india.jpg","Watch Now!!!"))
        liveList.add(LiveListData("https://image.shutterstock.com/shutterstock/photos/1886505013/display_1500/stock-photo-hanuman-indian-god-hanuman-ji-wallpaper-d-illustration-1886505013.jpg","Maha Navami"))
        liveList.add(LiveListData("https://cdn.wallpapersafari.com/34/22/YZ3pfP.jpg","Buddha Jayanthi"))
        liveList.add(LiveListData("https://1.bp.blogspot.com/-HWEutzqOaYg/Xy52fakClDI/AAAAAAAAGck/fjAc93z2m1ATUYloVuQxedPB4NLafbESgCLcBGAsYHQ/s1600/lord-ganesha-hindu-gods-wallpaper-picture-images-background-stock-photos-divyatattva-india.jpg","Watch Now!!!"))
        liveList.add(LiveListData("https://image.shutterstock.com/shutterstock/photos/1886505013/display_1500/stock-photo-hanuman-indian-god-hanuman-ji-wallpaper-d-illustration-1886505013.jpg","Maha Navami"))
        liveList.add(LiveListData("https://cdn.wallpapersafari.com/34/22/YZ3pfP.jpg","Buddha Jayanthi"))
        liveList.add(LiveListData("https://1.bp.blogspot.com/-HWEutzqOaYg/Xy52fakClDI/AAAAAAAAGck/fjAc93z2m1ATUYloVuQxedPB4NLafbESgCLcBGAsYHQ/s1600/lord-ganesha-hindu-gods-wallpaper-picture-images-background-stock-photos-divyatattva-india.jpg","Watch Now!!!"))
        liveList.add(LiveListData("https://image.shutterstock.com/shutterstock/photos/1886505013/display_1500/stock-photo-hanuman-indian-god-hanuman-ji-wallpaper-d-illustration-1886505013.jpg","Maha Navami"))

        binding.rvLive.adapter = LiveStreamAdapter(liveList,view)

    }
}