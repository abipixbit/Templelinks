package com.example.templelinks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.TempleListItemLargeBinding
import com.example.templelinks.extensions.glide


class HomeCategoryAdapterLarge : ListAdapter<Temple,HomeCategoryAdapterLarge.ViewHolder>(DiffCallBack()) {

    class ViewHolder(val binding : TempleListItemLargeBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryAdapterLarge.ViewHolder {
        return ViewHolder(TempleListItemLargeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeCategoryAdapterLarge.ViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.itemView.glide(currentItem?.imageUrl.toString(),holder.binding.ivTemples)
        holder.binding.tvTempleName.text = currentItem?.locale?.name
    }


}

class DiffCallBack : DiffUtil.ItemCallback<Temple>() {
    override fun areItemsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }

}
