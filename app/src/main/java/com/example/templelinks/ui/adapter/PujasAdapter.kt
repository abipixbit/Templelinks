package com.example.templelinks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.R
import com.example.templelinks.TempleApplication
import com.example.templelinks.data.model.Puja
import com.example.templelinks.databinding.PoojaBookingButtonListItemBinding

class PujasAdapter(val descriptionClick : (Puja) -> Unit, val selectPuja : (Puja, Int) -> Unit) : ListAdapter <Puja, PujasAdapter.PujasViewHolder>(PujasDiffCall()) {

    class PujasViewHolder(val binding : PoojaBookingButtonListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PujasAdapter.PujasViewHolder {
    return PujasViewHolder(PoojaBookingButtonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PujasAdapter.PujasViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.binding.ivTick.visibility =
            if (currentItem.isSelected) { View.VISIBLE }
            else { View.GONE }

        holder.binding.apply {
            tvItemName.text = currentItem.translation.pujaName
            tvPoojaPrice.text = TempleApplication.appContext.getString(R.string.pooja_price, currentItem.price)

            ivPoojaHelp.setOnClickListener {
                descriptionClick(currentItem)
            }
        }

        holder.itemView.setOnClickListener {
           selectPuja(currentItem, position)
//           notifyItemChanged(position)
        }

    }
}

class PujasDiffCall : DiffUtil.ItemCallback<Puja>() {

    override fun areItemsTheSame(oldItem: Puja, newItem: Puja): Boolean {
        return oldItem.deityId == newItem.deityId
    }

    override fun areContentsTheSame(oldItem: Puja, newItem: Puja): Boolean {
        return oldItem.deityId == newItem.deityId
    }

}
