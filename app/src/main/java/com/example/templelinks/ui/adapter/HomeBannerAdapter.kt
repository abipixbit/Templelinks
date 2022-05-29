package com.example.templelinks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.Banners
import com.example.templelinks.databinding.HomeBannerSliderBinding
import com.example.templelinks.extensions.glide

class HomeBannerAdapter (private val bannerList : List<Banners>) : RecyclerView.Adapter<HomeBannerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding : HomeBannerSliderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerAdapter.MyViewHolder {
        return MyViewHolder(HomeBannerSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeBannerAdapter.MyViewHolder, position: Int) {
        val currentItem = bannerList[position]
        holder.itemView.glide(currentItem.imageUrl.toString(), holder.binding.ivHomeBanner)
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }
}






