package com.example.templelinks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.R
import com.example.templelinks.databinding.ButtonListItemBinding

class GalleryButtonAdapter(private val buttonText : List<String>) : RecyclerView.Adapter<GalleryButtonAdapter.ViewHolder>() {

    class ViewHolder(val binding : ButtonListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryButtonAdapter.ViewHolder {
        return ViewHolder(ButtonListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GalleryButtonAdapter.ViewHolder, position: Int) {
        val currentItem = buttonText[position]
        holder.binding.button.text = currentItem

    }

    override fun getItemCount(): Int {
        return buttonText.size
    }
}