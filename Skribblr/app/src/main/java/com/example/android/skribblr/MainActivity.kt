package com.example.android.skribblr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.skribblr.adapter.RecyclerViewAdapter
import com.example.android.skribblr.data.DataSource
import com.example.android.skribblr.databinding.ActivityMainBinding
import com.example.android.skribblr.model.ViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView=binding.recyclerView
        recyclerView.layoutManager=StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val list = mutableListOf<ViewModel>()
        val adapter = RecyclerViewAdapter(list)
        recyclerView.adapter = adapter

        binding.textInLayout.visibility = View.INVISIBLE

        binding.fab.setOnClickListener {
            //makes text view visible on screen so a new task can be added
            binding.textInLayout.visibility=View.VISIBLE

            binding.submitButton.setOnClickListener {
                //mutableList is not saving to memory
                val saveList=DataSource().skribblList()
                if (saveList.isNotEmpty()){
                    saveList[0]
                }

                //page initially starts blank
                //can try any() call to see if list is empty and if it's not display what has  been entered
                val inputString=binding.editText.text.toString()
                list.add(ViewModel(inputString))
                saveList.add(inputString)
                adapter.notifyDataSetChanged()
                binding.editText.text.clear()
            }
            //TODO refactor after navigation lesson to use fragment
            // setContentView(R.layout.fragment_new_skribbl)
        }
    }
}