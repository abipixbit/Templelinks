package com.example.templelinks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.model.response.TemplesResponse
import com.example.templelinks.databinding.TempleListItemBinding
import com.example.templelinks.extensions.glide


class TemplesAdapter(private val templeList : List<Temple>?) : RecyclerView.Adapter<TemplesAdapter.ViewHolder>() {

    class ViewHolder(val binding : TempleListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplesAdapter.ViewHolder {
        return ViewHolder(TempleListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TemplesAdapter.ViewHolder, position: Int) {
        val currentItem = templeList?.get(position)


        holder.itemView.glide(currentItem?.imageUrl.toString(),holder.binding.ivTemples)

        holder.binding.tvTempleName.text = currentItem?.locale?.name


    }

    override fun getItemCount(): Int {
        return templeList?.size ?: 0
    }
}