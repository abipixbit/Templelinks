package com.example.templelinks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.TempleListItemSmallBinding
import com.example.templelinks.extensions.glide

class HomeCategoryAdapterSmall : ListAdapter<Temple, HomeCategoryAdapterSmall.ViewHolder>(HomeCategorySmallDiffUtil()) {

    class ViewHolder(val binding : TempleListItemSmallBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryAdapterSmall.ViewHolder {
        return ViewHolder(TempleListItemSmallBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeCategoryAdapterSmall.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.itemView.glide(currentItem?.imageUrl.toString(),holder.binding.ivTempleSmall)
        holder.binding.tvTempleSmall.text = currentItem?.locale?.name
    }


}

class HomeCategorySmallDiffUtil : DiffUtil.ItemCallback<Temple>() {
    override fun areItemsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }

}
