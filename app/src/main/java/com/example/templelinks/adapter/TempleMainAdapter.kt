package com.example.templelinks.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.templelinks.data.model.response.Temple
import com.example.templelinks.data.model.response.TemplesResponse
import com.example.templelinks.databinding.TempleMainListItemBinding

class TempleMainAdapter(private val homeCategory : List<TemplesResponse>) : RecyclerView.Adapter<TempleMainAdapter.ViewHolder>() {

    class ViewHolder(val binding : TempleMainListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempleMainAdapter.ViewHolder {
       return ViewHolder(TempleMainListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TempleMainAdapter.ViewHolder, position: Int) {
        val currentItem = homeCategory[position]
        holder.binding.tvCategoryName.text = currentItem.locale?.name

        loadChildRecyclerView(holder.binding.rvTempleList,currentItem.temples!!)
    }

    override fun getItemCount(): Int {
        return homeCategory.size
    }

    private fun loadChildRecyclerView(recyclerView : RecyclerView, templeList : List<Temple> ) {

        recyclerView.adapter = TemplesAdapter(templeList)


    }

}