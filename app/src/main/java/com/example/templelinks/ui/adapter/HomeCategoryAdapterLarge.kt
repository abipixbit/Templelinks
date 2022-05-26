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
import com.example.templelinks.databinding.TempleListItemLargeBinding
import com.example.templelinks.extensions.glide
import com.example.templelinks.extensions.snackBarLike
import com.example.templelinks.extensions.toast
import com.like.LikeButton
import com.like.OnLikeListener


class HomeCategoryAdapterLarge : ListAdapter<Temple,HomeCategoryAdapterLarge.ViewHolder>(HomeCategoryLargeDiffUtil()) {

    class ViewHolder(val binding : TempleListItemLargeBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryAdapterLarge.ViewHolder {
        return ViewHolder(TempleListItemLargeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeCategoryAdapterLarge.ViewHolder, position: Int) {

        holder.itemView.animation = AnimationUtils.loadAnimation(TempleApplication.appContext,R.anim.anim_recycler_view)


        val currentItem = getItem(position)
        holder.itemView.glide(currentItem?.imageUrl.toString(),holder.binding.ivTemples)
        holder.binding.tvTempleName.text = currentItem?.locale?.name

        holder.binding.likeButton.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
                holder.binding.ivLikeButtonBackground.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext,R.color.like_color))
                holder.itemView.snackBarLike("Liked")
                TempleApplication.appContext.toast(currentItem.id.toString())
            }

            override fun unLiked(likeButton: LikeButton?) {
                holder.binding.ivLikeButtonBackground.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(TempleApplication.appContext,R.color.unlike_color))
                holder.itemView.snackBarLike("Unliked")
            }
        })
    }

}

class HomeCategoryLargeDiffUtil : DiffUtil.ItemCallback<Temple>() {
    override fun areItemsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: Temple, newItem: Temple): Boolean {
        return false
    }

}
