package com.example.android.testlistwithfragment

import com.example.android.testlistwithfragment.adapter.RecyclerViewAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.android.testlistwithfragment.databinding.ActivityMainBinding
import com.example.android.testlistwithfragment.recycler.RecyclerViewModel

private lateinit var binding: ActivityMainBinding
private val list = mutableListOf<RecyclerViewModel>()
private val adapter = RecyclerViewAdapter(list)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        binding.appBarMain.fab?.setOnClickListener { view ->
            navController.navigate(R.id.newItemFragment)
        }

    }
//todo continue to add from test navigation. and then change look of buttons

}