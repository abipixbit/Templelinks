package com.example.templelinks.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.templelinks.databinding.GodsListItemBinding
import com.example.templelinks.extensions.glide

class GodListAdapter(private val gods : List<String>, val view : View) : RecyclerView.Adapter<GodListAdapter.MyViewHolder>() {

    class MyViewHolder(val binding : GodsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GodListAdapter.MyViewHolder {
        return MyViewHolder(GodsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GodListAdapter.MyViewHolder, position: Int) {

        val currentItem = gods[position]

        view.glide(currentItem,holder.binding.imageView)

    }

    override fun getItemCount(): Int {
        return gods.size
    }
}


