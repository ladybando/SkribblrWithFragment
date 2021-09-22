package com.example.android.skribblrfragment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.skribblrfragment.INTENT_DATA_NAME
import com.example.android.skribblrfragment.ListOfItemsActivity
import com.example.android.skribblrfragment.R
import com.example.android.skribblrfragment.databinding.ActivityListOfItemsBinding
import com.example.android.skribblrfragment.databinding.ListItemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TaskViewAdapter(
    private val context: Context,
    private val dataSet: MutableList<String>,
) :
    RecyclerView.Adapter<TaskViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.listOfItemsView.text = dataSet[position]

            binding.listOfItemsView.setOnClickListener {
                val intent = Intent(context, ListOfItemsActivity::class.java)
                intent.putExtra(INTENT_DATA_NAME, dataSet[position])
                context.startActivity(intent)
            }
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
    }

    override fun getItemCount(): Int = dataSet.size

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
