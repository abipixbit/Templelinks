package com.example.templelinks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.LiveListItemBinding
import com.example.templelinks.extensions.glide

class FavouriteAdapter (val itemClick : (Temple) -> Unit) : ListAdapter<Temple, FavouriteAdapter.FavouriteViewHolder>(FavouriteDiffUtil()) {

    class FavouriteViewHolder(val binding : LiveListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapter.FavouriteViewHolder {
        return FavouriteViewHolder(LiveListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavouriteAdapter.FavouriteViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.tvLiveTitle.text = currentItem.locale?.name
        holder.itemView.glide(currentItem.imageUrl.toString(), holder.binding.ivLive)

        holder.itemView.setOnClickListener {
            itemClick(currentItem)
        }


    }
}

class FavouriteDiffUtil : DiffUtil.ItemCallback<Temple>() {
    override fun areItemsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }

}
