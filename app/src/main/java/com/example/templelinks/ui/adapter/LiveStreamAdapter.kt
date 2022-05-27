package com.example.templelinks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.LiveListData
import com.example.templelinks.databinding.LiveListItemBinding
import com.example.templelinks.extensions.glide

class LiveStreamAdapter(private val liveList : List<LiveListData>, private val view : View) : RecyclerView.Adapter<LiveStreamAdapter.ViewHolder>() {


    class ViewHolder(val binding : LiveListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveStreamAdapter.ViewHolder {
        return ViewHolder(LiveListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: LiveStreamAdapter.ViewHolder, position: Int) {
        val currentItem = liveList[position]

        holder.binding.tvLiveTitle.text = currentItem.title
        view.glide(currentItem.image, holder.binding.ivLive)

    }

    override fun getItemCount(): Int {
        return liveList.size
    }
}