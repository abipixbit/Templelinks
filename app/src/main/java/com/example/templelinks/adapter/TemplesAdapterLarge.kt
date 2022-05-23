package com.example.templelinks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.TempleListItemLargeBinding
import com.example.templelinks.extensions.glide


class TemplesAdapterLarge(private val templeList : List<Temple>?) : RecyclerView.Adapter<TemplesAdapterLarge.ViewHolder>() {

    class ViewHolder(val binding : TempleListItemLargeBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplesAdapterLarge.ViewHolder {
        return ViewHolder(TempleListItemLargeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TemplesAdapterLarge.ViewHolder, position: Int) {
        val currentItem = templeList?.get(position)

        holder.itemView.glide(currentItem?.imageUrl.toString(),holder.binding.ivTemples)
        holder.binding.tvTempleName.text = currentItem?.locale?.name
    }

    override fun getItemCount(): Int {
        return templeList?.size ?: 0
    }
}