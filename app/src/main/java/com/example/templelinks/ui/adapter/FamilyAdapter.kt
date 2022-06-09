package com.example.templelinks.ui.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.Families
import com.example.templelinks.databinding.FamilyMemberListItemBinding

class FamilyAdapter(val addItemClick : (Families)-> Unit, val removeItemClick : (Families)-> Unit) : ListAdapter<Families, FamilyAdapter.FamilyViewHolder>(FamilyDIffUtil()) {

    class FamilyViewHolder(val binding : FamilyMemberListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyAdapter.FamilyViewHolder {
        return FamilyViewHolder(FamilyMemberListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FamilyAdapter.FamilyViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.binding.apply {
            tvMemberName.text = currentItem.name
            tvMemberNakshathra.text = currentItem.nakshathra?.locale?.nakshathraName

            if (currentItem.isSelected == true) {
                ivCheck.visibility = View.VISIBLE
            }
            else {
                ivCheck.visibility = View.GONE
            }

            holder.itemView.setOnClickListener {
                    if (currentItem.isSelected == true) {
                    removeItemClick(currentItem)
                    currentItem.isSelected = false
                }
                else {
                    addItemClick(currentItem)
                    currentItem.isSelected = true
                }
                notifyItemChanged(position)
            }
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
