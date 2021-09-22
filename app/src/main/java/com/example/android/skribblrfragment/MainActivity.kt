package com.example.android.skribblrfragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.skribblrfragment.adapter.TaskViewAdapter
import com.example.android.skribblrfragment.databinding.ActivityMainBinding
import com.example.android.skribblrfragment.databinding.ListItemBinding
import com.example.android.skribblrfragment.model.SharedViewModel
//TODO refactor and use viewModel
// TODO then liveData
//  TODO then refactor redundant code
const val INTENT_DATA_NAME = "data"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by viewModels()
    private var recyclerView : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        val adapter = TaskViewAdapter(this, viewModel.taskList)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.submitButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ListOfItemsActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Code that's called when we come back from SecondActivity
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val newUserInput =
                data?.getStringExtra(INTENT_DATA_NAME)

            val listItemBinding = ListItemBinding.inflate(layoutInflater)
            listItemBinding.listOfItemsView.text = newUserInput
            viewModel.taskList.add(newUserInput.toString())

        }
    }
}