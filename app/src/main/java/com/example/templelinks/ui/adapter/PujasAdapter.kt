package com.example.templelinks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.R
import com.example.templelinks.TempleApplication
import com.example.templelinks.data.model.Pujas
import com.example.templelinks.databinding.PoojaBookingButtonListItemBinding

class PujasAdapter(val descriptionClick : (Pujas) -> Unit, val selectFamClick : (Int) -> Unit, var check : Boolean) : ListAdapter <Pujas, PujasAdapter.PujasViewHolder>(PujasDiffCall()) {

    class PujasViewHolder(val binding : PoojaBookingButtonListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PujasAdapter.PujasViewHolder {
    return PujasViewHolder(PoojaBookingButtonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PujasAdapter.PujasViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.binding.tvItemName.text = currentItem.translation.pujaName
        holder.binding.tvPoojaPrice.text = TempleApplication.appContext.getString(R.string.pooja_price, currentItem.price)

        holder.binding.ivPoojaHelp.setOnClickListener {
            descriptionClick(currentItem)
        }

        holder.itemView.setOnClickListener {
            selectFamClick(currentItem.translation.pujaId)
            if (check) {
                holder.binding.ivTick.visibility = View.VISIBLE
                check = false
            }
            else {
                holder.binding.ivTick.visibility = View.GONE
                check = true
            }
        }
    }
}

class PujasDiffCall : DiffUtil.ItemCallback<Pujas>() {

    override fun areItemsTheSame(oldItem: Pujas, newItem: Pujas): Boolean {
        return oldItem.deityId == newItem.deityId
    }

    override fun areContentsTheSame(oldItem: Pujas, newItem: Pujas): Boolean {
        return oldItem.deityId == newItem.deityId
    }

}
