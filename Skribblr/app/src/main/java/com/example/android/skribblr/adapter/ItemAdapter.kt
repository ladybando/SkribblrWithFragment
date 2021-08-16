package com.example.android.skribblr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.skribblr.R
import com.example.android.skribblr.model.Skribble

//TODO add square checks for completed tasks

class ItemAdapter(private val context: Context, private val dataset: List<Skribble>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        //find text view created in list_item
        val textView: TextView =  view.findViewById(R.id.item_title)
    }
    //abstract methods that need to be implemented from [RecyclerView] superclass

    //called by layout manager to create new view holders for [RecyclerView]
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //create a new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    //called by layout manager to replace contents of list view
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceID)
    }

    /*getItemCount() method returns size of dataset. App data is in dataset property passed into the [ItemAdapter] constructor*/
    override fun getItemCount(): Int {
        return dataset.size //can also be `override fun getItemCount() = dataset.size`
    }
}