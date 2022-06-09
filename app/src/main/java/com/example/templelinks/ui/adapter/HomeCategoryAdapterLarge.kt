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
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.TempleListItemLargeBinding
import com.example.templelinks.extensions.glide


class HomeCategoryAdapterLarge(val favClick : (List<Temple>) -> Unit, val itemClick : (Temple) -> Unit) : ListAdapter<Temple, HomeCategoryAdapterLarge.ViewHolder>(HomeCategoryLargeDiffUtil()) {

    class ViewHolder(val binding : TempleListItemLargeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryAdapterLarge.ViewHolder {
        return ViewHolder(TempleListItemLargeBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }
    override fun onBindViewHolder(holder: HomeCategoryAdapterLarge.ViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.itemView.apply {
            glide(currentItem?.imageUrl.toString(), holder.binding.ivTemples)
            setOnClickListener { itemClick(currentItem) }
        }
        holder.binding.apply {
            tvTempleName.text = currentItem?.locale?.name

            if (currentItem.isFavourite) {
                ivLikeButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext, R.color.app_color))
                ivLikeButtonBackground.apply {
                    imageTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext, R.color.like_color))
                    setOnClickListener { favClick(listOf(currentItem))
                        notifyItemChanged(position) }
                }
            }

            else {
                ivLikeButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext, R.color.unlike_grey_color))
                holder.binding.ivLikeButtonBackground.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext, R.color.unlike_color))
            }
        }
    }
}

class HomeCategoryLargeDiffUtil : DiffUtil.ItemCallback<Temple>() {
    override fun areItemsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.id == newItem.id
    }
}
