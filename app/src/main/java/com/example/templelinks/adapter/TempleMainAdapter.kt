package com.example.templelinks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.databinding.TempleMainListItemBinding

class TempleMainAdapter() : RecyclerView.Adapter<TempleMainAdapter.ViewHolder>() {

    class ViewHolder(val binding : TempleMainListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempleMainAdapter.ViewHolder {
        return ViewHolder(TempleMainListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TempleMainAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}