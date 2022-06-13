package com.example.templelinks.ui.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.R
import com.example.templelinks.TempleApplication
import com.example.templelinks.data.model.Families
import com.example.templelinks.data.model.Puja
import com.example.templelinks.databinding.ConfirmPujaListItemBinding


class ConfrimPoojaAdapter : ListAdapter<Puja, ConfrimPoojaAdapter.ConfirmPoojaViewHolder>(ConfirmPoojaDiffUtils()) {

    class ConfirmPoojaViewHolder(val binding : ConfirmPujaListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmPoojaViewHolder {
        return ConfirmPoojaViewHolder(ConfirmPujaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ConfirmPoojaViewHolder, position: Int) {
        val currentItem = getItem(position)
        Log.d("ConfirmAdapter", currentItem.toString())
        holder.binding.apply {
            tvConfirmPoojaName.text = currentItem.translation.pujaName
            tvConfirmPoojaAmount.text = TempleApplication.appContext.getString(R.string.pooja_price, currentItem.price)
            loadFamilies(this.rvConfirmPoojaFamilyName, currentItem.selectedFamilies)
        }

    }

    private fun loadFamilies(recyclerView : RecyclerView, families : List<Families>? ) {
        val confirmFamilyAdapter = ConfirmPoojaFamilyAdapter()
        recyclerView.adapter = confirmFamilyAdapter
        confirmFamilyAdapter.submitList(families)
    }

}

class ConfirmPoojaDiffUtils : DiffUtil.ItemCallback<Puja>() {
    override fun areItemsTheSame(oldItem: Puja, newItem: Puja): Boolean {
        return oldItem.deityId == newItem.deityId
    }

    override fun areContentsTheSame(oldItem: Puja, newItem: Puja): Boolean {
        return oldItem.deityId == newItem.deityId
    }

}
