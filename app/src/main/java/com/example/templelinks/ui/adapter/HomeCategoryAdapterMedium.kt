package com.example.templelinks.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.R
import com.example.templelinks.TempleApplication
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.databinding.TempleListItemMediumBinding
import com.example.templelinks.extensions.glide

class HomeCategoryAdapterMedium(val favClick : (List<Temple>) -> Unit, val itemClick : (Temple) -> Unit) : ListAdapter<Temple,HomeCategoryAdapterMedium.ViewHolder>(HomeCategoryMediumDiffUtilCall()) {

    class ViewHolder(val binding : TempleListItemMediumBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryAdapterMedium.ViewHolder {
        return ViewHolder(TempleListItemMediumBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: HomeCategoryAdapterMedium.ViewHolder, position: Int) {

        val temple = currentList
//        holder.itemView.animation = AnimationUtils.loadAnimation(TempleApplication.appContext, R.anim.anim_recycler_view)

        val currentItem = getItem(position)

        holder.binding.ivLikeButtonBackground.setOnClickListener {
            favClick(listOf(currentItem))
            notifyItemChanged(position)
        }

        holder.itemView.setOnClickListener {
            itemClick(currentItem)
        }

        if (currentItem.isFavourite) {
            holder.binding.ivLikeButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext,R.color.app_color))
            holder.binding.ivLikeButtonBackground.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(TempleApplication.appContext,R.color.like_color))
        }

        else {
            holder.binding.ivLikeButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext,R.color.grey_color))
            holder.binding.ivLikeButtonBackground.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext,R.color.unlike_color))
        }

        holder.itemView.glide(currentItem?.imageUrl.toString(), holder.binding.ivTemples)
        holder.binding.tvTempleName.text = currentItem?.locale?.name
    }

}

class HomeCategoryMediumDiffUtilCall : DiffUtil.ItemCallback<Temple>() {

    override fun areItemsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

}
