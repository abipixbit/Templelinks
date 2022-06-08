package com.example.templelinks.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.R
import com.example.templelinks.TempleApplication
import com.example.templelinks.data.model.Families
import com.example.templelinks.data.model.Pujas
import com.example.templelinks.databinding.ConfirmPujaListItemBinding


class ConfrimPoojaAdapter : ListAdapter<Pujas, ConfrimPoojaAdapter.ConfirmPoojaViewHolder>(ConfirmPoojaDiffUtils()) {

    class ConfirmPoojaViewHolder(val binding : ConfirmPujaListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmPoojaViewHolder {
        return ConfirmPoojaViewHolder(ConfirmPujaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ConfirmPoojaViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.tvConfirmPoojaName.text = currentItem.translation.pujaName
        holder.binding.tvConfirmPoojaAmount.text = TempleApplication.appContext.getString(R.string.pooja_price, currentItem.price)

        loadFamilies(holder.binding.rvConfirmPoojaFamilyName, currentItem.selectedFamilies)

    }

    private fun loadFamilies(recyclerView : RecyclerView, families : List<Families>? ) {
        val confirmFamilyAdapter = ConfirmPoojaFamilyAdapter()
        recyclerView.adapter = confirmFamilyAdapter
        confirmFamilyAdapter.submitList(families)
    }

}

class ConfirmPoojaDiffUtils : DiffUtil.ItemCallback<Pujas>() {
    override fun areItemsTheSame(oldItem: Pujas, newItem: Pujas): Boolean {
        return oldItem.deityId == newItem.deityId
    }

    override fun areContentsTheSame(oldItem: Pujas, newItem: Pujas): Boolean {
        return oldItem.deityId == newItem.deityId
    }

}
