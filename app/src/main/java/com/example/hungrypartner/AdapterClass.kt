package com.example.hungrypartner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass (private val dataList: List<DataClass>): RecyclerView.Adapter<AdapterClass.ViewholderClass>(){

    var onItemClick: ((DataClass) -> Unit)? = null

    class ViewholderClass(itemView: View): RecyclerView.ViewHolder(itemView){
        val rvImage: ImageView = itemView.findViewById(R.id.image)
        val rvTitle: TextView = itemView.findViewById(R.id.Title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewholderClass {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewholderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size

    }

    override fun onBindViewHolder(holder: ViewholderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImage.setImageResource(currentItem.dataImage)
        holder.rvTitle.text = currentItem.dataTitle

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }

    }
}