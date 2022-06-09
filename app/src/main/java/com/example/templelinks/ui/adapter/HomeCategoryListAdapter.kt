package com.example.templelinks.ui.adapter

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

class HomeCategoryListAdapter(val favClick : (List<Temple>) -> Unit, val itemClick : (Temple) -> Unit) : ListAdapter<TemplesResponse, HomeCategoryListAdapter.HomeCategoryViewHolder>(HomeCategoryDiffCallBack()) {

    class HomeCategoryViewHolder(val binding: TempleMainListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryListAdapter.HomeCategoryViewHolder {
        return HomeCategoryViewHolder(TempleMainListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: HomeCategoryListAdapter.HomeCategoryViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.binding.apply {
            tvCategoryName.text = currentItem.locale?.name

            if (currentItem.temples?.size == 0) {
               tvCategoryName.visibility = View.GONE
               tvSeeMore.visibility = View.GONE
               rvTempleList.visibility = View.GONE
            }
        }

        when (currentItem.viewType) {

            "Large" -> {
                Log.d("Large", currentItem.temples.toString())
                loadHomeCategoryLarge(holder.binding.rvTempleList, currentItem.temples)
            }

            "Medium" -> {
                Log.d("Medium", currentItem.temples.toString())
                loadHomeCategoryMedium(holder.binding.rvTempleList, currentItem.temples)
            }

            "Small" -> {
                Log.d("Small", currentItem.temples.toString())
                loadHomeCategorySmall(holder.binding.rvTempleList, currentItem.temples)
            }

        }

    }

    private fun loadHomeCategoryLarge(recyclerView: RecyclerView, templeList: List<Temple>?) {
        val homeAdapterLarge = HomeCategoryAdapterLarge({ currentTemple ->
            favClick(currentTemple)
        }, { currentTemple ->
            itemClick(currentTemple)
        })

        recyclerView.adapter = homeAdapterLarge
        homeAdapterLarge.submitList(templeList)
        recyclerView.itemAnimator?.changeDuration = 0
    }

    private fun loadHomeCategoryMedium(recyclerView: RecyclerView, templeList: List<Temple>?) {
        val homeAdapterMedium = HomeCategoryAdapterMedium({ currentTemple ->
            favClick(currentTemple)
        }, { currentTemple ->
            itemClick(currentTemple)
        })

        recyclerView.adapter = homeAdapterMedium
        homeAdapterMedium.submitList(templeList)
        recyclerView.itemAnimator?.changeDuration = 0
    }

    private fun loadHomeCategorySmall(recyclerView: RecyclerView, templeList: List<Temple>?) {
        val homeAdapterSmall = HomeCategoryAdapterSmall{ currentTemple->
            itemClick(currentTemple)
        }
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
