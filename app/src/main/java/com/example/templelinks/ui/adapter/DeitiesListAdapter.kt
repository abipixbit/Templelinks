package com.example.templelinks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.Deities
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.DeitiesListItemBinding
import com.example.templelinks.extensions.glide

class DeitiesListAdapter (val itemClick : (Deities) -> Unit) : ListAdapter<Deities, DeitiesListAdapter.DeitiesViewHolder>(DeitiesDiffCallBack()) {



    class DeitiesViewHolder(val binding : DeitiesListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeitiesViewHolder {

        return DeitiesViewHolder(DeitiesListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: DeitiesViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.itemView.apply {
            glide(currentItem.imageUrl.toString(), holder.binding.ivGodsListItem)
            setOnClickListener {
                itemClick(currentItem)
            }
        }
    }

}

class DeitiesDiffCallBack : DiffUtil.ItemCallback<Deities>() {

    override fun areItemsTheSame(oldItem: Deities, newItem: Deities): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Deities, newItem: Deities): Boolean {
        return oldItem.id == newItem.id
    }

}


