package com.example.templelinks.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.Families
import com.example.templelinks.databinding.ConfirmPujaMemberListItemBinding

class ConfirmPoojaFamilyAdapter(var currentItem : (Families)->Unit) : ListAdapter<Families, ConfirmPoojaFamilyAdapter.ConfirmFamViewHolder>(ConfirmFamDiffUtils()) {

    class ConfirmFamViewHolder(val binding: ConfirmPujaMemberListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmFamViewHolder {
        return ConfirmFamViewHolder(ConfirmPujaMemberListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ConfirmFamViewHolder, position: Int) {
        val currentItem = getItem(position)
        Log.d("Confirm", currentItem.toString())
        holder.binding.apply {

            var count = tvConfirmCount.text.toString().toInt()

            tvConfirmFamName.text = currentItem.name

            btnAdd.setOnClickListener { count += 1
                tvConfirmCount.text = count.toString()
                currentItem.count = count
                currentItem(currentItem)
            }

            btnMinus.setOnClickListener {
                if (count > 1) { count -= 1 }
                tvConfirmCount.text = count.toString()
                currentItem.count = count
                currentItem(currentItem)
            }

        }

    }
}

class ConfirmFamDiffUtils : DiffUtil.ItemCallback<Families>() {
    override fun areItemsTheSame(oldItem: Families, newItem: Families): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Families, newItem: Families): Boolean {
        return oldItem.id == newItem.id
    }
}


