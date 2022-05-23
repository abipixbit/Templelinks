package com.example.templelinks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.TempleListItemMediumBinding
import com.example.templelinks.extensions.glide

class TemplesAdapterMedium(private val templeList : List<Temple>?) : RecyclerView.Adapter<TemplesAdapterMedium.ViewHolder>() {

    class ViewHolder(val binding : TempleListItemMediumBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplesAdapterMedium.ViewHolder {
        return ViewHolder(TempleListItemMediumBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TemplesAdapterMedium.ViewHolder, position: Int) {
        val currentItem = templeList?.get(position)

        holder.itemView.glide(currentItem?.imageUrl.toString(),holder.binding.ivTemples)
        holder.binding.tvTempleName.text = currentItem?.locale?.name
    }

    override fun getItemCount(): Int {
        return templeList?.size ?: 0
    }
}