package com.example.android.skribblrfragment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.skribblrfragment.adapter.TaskViewAdapter
import com.example.android.skribblrfragment.databinding.ActivityMainBinding
import com.example.android.skribblrfragment.model.SharedViewModel
//TODO refactor and use viewModel
// TODO then liveData
//  TODO then refactor redundant code
val list = mutableListOf<SharedViewModel>()
const val INTENT_DATA_NAME = "data"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var recyclerView : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        val adapter = TaskViewAdapter(this, list)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.submitButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewItemActivity::class.java)
            startActivity(intent)
        }
    }
}