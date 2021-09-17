package com.example.android.skribblrfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.skribblrfragment.R
import com.example.android.skribblrfragment.fragments.ListItemFragmentDirections
import com.example.android.skribblrfragment.model.SharedViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecyclerViewAdapter(private val dataSet: MutableList<SharedViewModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,
    ): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text=dataSet[position].notes
        holder.fab.setOnClickListener {
            val action = ListItemFragmentDirections.actionListItemFragmentToNewItemFragment(newUserInput = holder.textView.text.toString())
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int=dataSet.size

    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val textView: TextView=view.findViewById(R.id.list_item_edit_text)
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
    }
}