package com.example.android.skribblrfragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.skribblrfragment.R
import com.example.android.skribblrfragment.databinding.ItemLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TaskViewAdapter(
    private val listener: Listener,
    private val context: Context,
    private var dataSet: MutableList<String>
) :
    RecyclerView.Adapter<TaskViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        binding.listOfItemsView.text = dataSet[position]

        binding.listOfItemsView.setOnLongClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.title)
                .setMessage(R.string.affirmation)
                .setCancelable(false)
                .setNegativeButton(R.string.decline) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(R.string.accept) { _, _ ->
                    dataSet.removeAt(position)
                    notifyItemRemoved(position)
                }
                .show()
            true
        }
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        private val textView = binding.listOfItemsView

        init {
            textView.setOnClickListener {
                listener.onTaskClicked(adapterPosition)
            }
        }
    }

    fun setTasks(listOfTask:MutableList<String>) {
        this.dataSet = listOfTask
        notifyItemChanged(0)
    }

    interface Listener{
        fun onTaskClicked(index: Int)
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> c8c57705b3cf3378fba7f3fed83449f3c88ca149
