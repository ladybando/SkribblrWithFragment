package com.example.android.skribblrfragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.skribblrfragment.adapter.TaskViewAdapter
import com.example.android.skribblrfragment.databinding.ActivityMainBinding
import com.example.android.skribblrfragment.databinding.ItemLayoutBinding
import com.example.android.skribblrfragment.model.SharedViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

//TODO refactor and use viewModel
// TODO then liveData
//  TODO then refactor redundant code
const val INTENT_DATA_NAME = "data"
const val INDEX = "index"

class MainActivity : AppCompatActivity(), TaskViewAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskViewAdapter
    private val viewModel: SharedViewModel by viewModels()
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        adapter = TaskViewAdapter(this, this, viewModel.taskList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.submitButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ListOfItemsActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Code that's called when we come back from SecondActivity
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val newUserInput =
                    data?.getStringExtra(INTENT_DATA_NAME)

                val listItemBinding = ItemLayoutBinding.inflate(layoutInflater)
                listItemBinding.listOfItemsView.text = newUserInput
                viewModel.taskList.add(newUserInput.toString())

            }
        }

    override fun onTaskClicked(index: Int) {
        val intent = Intent(this, ListOfItemsActivity::class.java)
        intent.putExtra(INDEX, index)
        intent.putExtra(INTENT_DATA_NAME, viewModel.taskList[index])
        position = index
        resultLauncher.launch(intent)
    }

/*    override fun onLongTaskClicked(context: Context) {
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.title)
            .setMessage(R.string.affirmation)
            .setCancelable(false)
            .setNegativeButton(R.string.decline) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.accept) { _, _ ->
                viewModel.taskList.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
            .show()
    }*/

}
