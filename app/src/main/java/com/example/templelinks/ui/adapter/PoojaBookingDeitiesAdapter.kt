package com.example.templelinks.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.R
import com.example.templelinks.TempleApplication
import com.example.templelinks.data.model.Deities
import com.example.templelinks.databinding.ButtonListItemBinding

class PoojaBookingDeitiesAdapter (val itemClick : (Int?) -> Unit) : ListAdapter<Deities, PoojaBookingDeitiesAdapter.PoojaBookingViewHolder>(PoojaBookingDiffCall()) {

    private var selectedItem : Int = 0

    class PoojaBookingViewHolder(val binding : ButtonListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoojaBookingDeitiesAdapter.PoojaBookingViewHolder {
        return PoojaBookingViewHolder(ButtonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PoojaBookingDeitiesAdapter.PoojaBookingViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (selectedItem == position) {
            holder.binding.btn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext, R.color.app_color))
            holder.binding.btn.setTextColor(ContextCompat.getColor(TempleApplication.appContext, R.color.white))
        }
        else {
            holder.binding.btn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext, R.color.white))
            holder.binding.btn.setTextColor(ContextCompat.getColor(TempleApplication.appContext, R.color.black))
        }

        holder.binding.btn.text = currentItem.translation.deitiesName
        holder.binding.btn.setOnClickListener {
            itemClick(currentItem.id)
            notifyItemChanged(selectedItem)
            selectedItem = holder.adapterPosition
            notifyItemChanged(selectedItem)
        }
    }
}

class PoojaBookingDiffCall : DiffUtil.ItemCallback<Deities>(){

    override fun areItemsTheSame(oldItem: Deities, newItem: Deities): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Deities, newItem: Deities): Boolean {
        return oldItem.id == newItem.id
    }

}
