package com.example.templelinks.ui.adapter

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.Families
import com.example.templelinks.databinding.FamilyMemberListItemBinding

class FamilyAdapter(val itemClick : (Families)-> Unit) : ListAdapter<Families, FamilyAdapter.FamilyViewHolder>(FamilyDIffUtil()) {

    class FamilyViewHolder(val binding : FamilyMemberListItemBinding) : RecyclerView.ViewHolder(binding.root)

    init {
        Log.d("FamilyAdapter", "Created")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyAdapter.FamilyViewHolder {
        return FamilyViewHolder(FamilyMemberListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FamilyAdapter.FamilyViewHolder, position: Int) {

        Log.d("FamilyAdapter", currentList.toString())
        
        val currentItem = getItem(position)
        holder.binding.apply {
            tvMemberName.text = currentItem.name
            tvMemberNakshathra.text = currentItem.nakshathra?.locale?.nakshathraName

            ivCheck.visibility =
                if (currentItem.isSelected == true) { View.VISIBLE }
                else { View.GONE }
        }

        holder.itemView.setOnClickListener {
            currentItem.isSelected = currentItem.isSelected != true
            itemClick(currentItem)
            notifyItemChanged(position)
        }
    }
}

class FamilyDIffUtil : DiffUtil.ItemCallback<Families>(){

    override fun areItemsTheSame(oldItem: Families, newItem: Families): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Families, newItem: Families): Boolean {
        return oldItem.id == newItem.id
    }

}
