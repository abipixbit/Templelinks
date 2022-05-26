package com.example.templelinks.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.model.response.TemplesResponse
import com.example.templelinks.databinding.TempleMainListItemBinding

class HomeCategoryListAdapter : ListAdapter<TemplesResponse, HomeCategoryListAdapter.HomeCategoryViewHolder>(HomeCategoryDiffCallBack()) {

    class HomeCategoryViewHolder(val binding : TempleMainListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryListAdapter.HomeCategoryViewHolder {
       return HomeCategoryViewHolder(TempleMainListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeCategoryListAdapter.HomeCategoryViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.binding.tvCategoryName.text = currentItem.locale?.name

        if (currentItem.viewType.equals("Large")) {
            Log.d("Large",currentItem.temples.toString())
            loadHomeCategoryLarge(holder.binding.rvTempleList,currentItem.temples!!)
        }
        else if (currentItem.viewType.equals("Medium")) {
            Log.d("Medium",currentItem.temples.toString())
            loadHomeCategoryMedium(holder.binding.rvTempleList,currentItem.temples!!)
        }
        else if (currentItem.viewType.equals("Small")) {
            Log.d("Small",currentItem.temples.toString())
            loadHomeCategorySmall(holder.binding.rvTempleList,currentItem.temples!!)
        }

        if (currentItem.temples?.size==0) {
            holder.binding.tvCategoryName.visibility = View.GONE
            holder.binding.tvSeeMore.visibility = View.GONE
            holder.binding.rvTempleList.visibility = View.GONE
        }

    }


    private fun loadHomeCategoryLarge(recyclerView : RecyclerView, templeList : List<Temple>) {
        val homeAdapterLarge = HomeCategoryAdapterLarge()
        recyclerView.adapter = homeAdapterLarge
        homeAdapterLarge.submitList(templeList)
    }

    private fun loadHomeCategoryMedium(recyclerView : RecyclerView, templeList : List<Temple> ) {
        val homeAdapterMedium = HomeCategoryAdapterMedium()
        recyclerView.adapter = homeAdapterMedium
        homeAdapterMedium.submitList(templeList)
    }

    private fun loadHomeCategorySmall(recyclerView : RecyclerView, templeList : List<Temple> ) {
        val homeAdapterSmall = HomeCategoryAdapterSmall()
        recyclerView.adapter = homeAdapterSmall
        homeAdapterSmall.submitList(templeList)
    }

}

class HomeCategoryDiffCallBack : DiffUtil.ItemCallback<TemplesResponse>() {
    override fun areItemsTheSame(oldItem: TemplesResponse, newItem: TemplesResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TemplesResponse, newItem: TemplesResponse): Boolean {
        return oldItem.id == newItem.id

    }

}
