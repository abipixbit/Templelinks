package com.example.templelinks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.TempleDetailButtonListItemBinding

class TempleDetailButtonAdapter : ListAdapter<Temple, TempleDetailButtonAdapter.ButtonViewHolder>(TempleDetailDiffUtils()) {

    class ButtonViewHolder(val binding : TempleDetailButtonListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempleDetailButtonAdapter.ButtonViewHolder {

        return ButtonViewHolder(TempleDetailButtonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TempleDetailButtonAdapter.ButtonViewHolder, position: Int) {

        val currentItem = getItem(position)

        if (currentItem.isPoojaBooking == true) {
            holder.binding.tvItemName.text = "Pooja Booking"
        }

        if (currentItem.isPrasada == true) {
            holder.binding.tvItemName.text = "Prasad"
        }

        if (currentItem.isDonation == true) {
            holder.binding.tvItemName.text = "Donation"
        }
        if (currentItem.isVirtualQueue == true) {
            holder.binding.tvItemName.text = "Virtual Queue"
        }

    }
}



class TempleDetailDiffUtils : DiffUtil.ItemCallback<Temple> (){
    override fun areItemsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }

}
