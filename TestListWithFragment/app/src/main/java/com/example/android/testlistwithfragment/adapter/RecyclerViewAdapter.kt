package com.example.android.testlistwithfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.testlistwithfragment.R
import com.example.android.testlistwithfragment.recycler.RecyclerViewModel

class RecyclerViewAdapter(private val dataSet: MutableList<RecyclerViewModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,
    ): ViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_item, parent, false)
        return ViewHolder(view)
    }

    //TODO find how to set the text in this text view (list_item) to the user input from activity_new_item)
    //TODO uncover if fragment is needed for this view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text=dataSet[position].notes
    }

    override fun getItemCount(): Int=dataSet.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView =view.findViewById(R.id.textView)
    }
}