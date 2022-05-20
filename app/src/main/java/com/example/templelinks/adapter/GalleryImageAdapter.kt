package com.example.templelinks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.databinding.GodImageListItemBinding
import com.example.templelinks.extensions.glide

class GalleryImageAdapter(private val gods : List<String>, val view : View) : RecyclerView.Adapter<GalleryImageAdapter.MyViewHolder>() {

    class MyViewHolder(val binding : GodImageListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryImageAdapter.MyViewHolder {
        return MyViewHolder(GodImageListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GalleryImageAdapter.MyViewHolder, position: Int) {

        val currentItem = gods[position]

        view.glide(currentItem,holder.binding.ivGods)

    }

    override fun getItemCount(): Int {
        return gods.size
    }
}