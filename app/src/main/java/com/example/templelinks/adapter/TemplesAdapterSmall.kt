package com.example.templelinks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.TempleListItemSmallBinding
import com.example.templelinks.extensions.glide

class TemplesAdapterSmall(private val templeList : List<Temple>?) : RecyclerView.Adapter<TemplesAdapterSmall.ViewHolder>() {

    class ViewHolder(val binding : TempleListItemSmallBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplesAdapterSmall.ViewHolder {
        return ViewHolder(TempleListItemSmallBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TemplesAdapterSmall.ViewHolder, position: Int) {
        val currentItem = templeList?.get(position)

        holder.itemView.glide(currentItem?.imageUrl.toString(),holder.binding.ivTempleSmall)
        holder.binding.tvTempleSmall.text = currentItem?.locale?.name
    }

    override fun getItemCount(): Int {
        return templeList?.size ?: 0
    }
}