package com.example.android.skribblr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.skribblr.R
import com.example.android.skribblr.model.ViewModel

class RecyclerViewAdapter(private val dataset:MutableList<ViewModel>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    //two by two grid list?
    //clickable to edit and/or delete
    //clicking opens to edit
    //long click to delete
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text=dataset[position].notes
    }

    override fun getItemCount(): Int=dataset.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView:TextView = view.findViewById(R.id.skribbl_text_view)
    }
}