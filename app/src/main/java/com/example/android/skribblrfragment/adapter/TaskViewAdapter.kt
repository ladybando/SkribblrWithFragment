package com.example.android.skribblrfragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.skribblrfragment.databinding.ItemLayoutBinding

class TaskViewAdapter(
    private val listener: Listener,
    private val context: Context,

    ) :
    RecyclerView.Adapter<TaskViewAdapter.ViewHolder>() {
    private var dataSet = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        binding.listOfItemsView.text = dataSet[position]
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        private val textView = binding.listOfItemsView

        init {
            textView.setOnClickListener {
                listener.onTaskClicked(adapterPosition)
            }
            textView.setOnLongClickListener {
                listener.onLongTaskClicked(context, adapterPosition)
                true
            }
        }
    }

    fun setTasks(listOfTask:MutableList<String>) {
        this.dataSet = listOfTask
        notifyItemChanged(0)
    }

    interface Listener{
        fun onTaskClicked(index: Int)
        fun onLongTaskClicked(context: Context, index: Int)
    }

}
