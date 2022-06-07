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

class FamilyAdapter(val addItemClick : (Int)-> Unit, val removeItemClick : (Int)-> Unit) : ListAdapter<Families, FamilyAdapter.FamilyViewHolder>(FamilyDIffUtil()) {


    class FamilyViewHolder(val binding : FamilyMemberListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyAdapter.FamilyViewHolder {
        return FamilyViewHolder(FamilyMemberListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FamilyAdapter.FamilyViewHolder, position: Int) {
        var check = false
        val currentItem = getItem(position)
        holder.binding.tvMemberName.text = currentItem.name
        holder.binding.tvMemberNakshathra.text = currentItem.nakshathra.locale.nakshathraName

        holder.itemView.setOnClickListener {
            holder.binding.ivCheck.visibility = if (check) {
                removeItemClick(currentItem.id)
                check = false
                View.GONE
            }
            else {
                addItemClick(currentItem.id)
                check = true
                View.VISIBLE
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
