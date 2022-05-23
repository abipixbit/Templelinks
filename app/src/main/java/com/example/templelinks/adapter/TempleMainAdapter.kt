package com.example.templelinks.adapter

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

        if (currentItem.viewType.equals("Large")) {
            loadChildRecyclerViewLarge(holder.binding.rvTempleList,currentItem.temples!!)
        }
        else if (currentItem.viewType.equals("Medium")) {
            loadChildRecyclerViewMedium(holder.binding.rvTempleList,currentItem.temples!!)
        }
        else if (currentItem.viewType.equals("Small")) {
            loadChildRecyclerViewSmall(holder.binding.rvTempleList,currentItem.temples!!)
        }

        if (currentItem.temples?.size==0) {
            holder.binding.tvCategoryName.visibility = View.GONE
            holder.binding.tvSeeMore.visibility = View.GONE
            holder.binding.rvTempleList.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return homeCategory.size
    }

    private fun loadChildRecyclerViewLarge(recyclerView : RecyclerView, templeList : List<Temple> ) {
        recyclerView.adapter = TemplesAdapterLarge(templeList)
    }

    private fun loadChildRecyclerViewMedium(recyclerView : RecyclerView, templeList : List<Temple> ) {
        recyclerView.adapter = TemplesAdapterMedium(templeList)
    }

    private fun loadChildRecyclerViewSmall(recyclerView : RecyclerView, templeList : List<Temple> ) {
        recyclerView.adapter = TemplesAdapterSmall(templeList)
    }

}