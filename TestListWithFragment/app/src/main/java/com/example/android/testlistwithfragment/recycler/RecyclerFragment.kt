package com.example.android.testlistwithfragment.recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android.testlistwithfragment.adapter.RecyclerViewAdapter
import com.example.android.testlistwithfragment.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private lateinit var recyclerViewModel: RecyclerViewModel
    private var _binding: FragmentRecyclerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        recyclerViewModel = ViewModelProvider(this).get(RecyclerViewModel::class.java)
        // Inflate the layout for this fragment
        _binding= FragmentRecyclerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerviewRecycler
        val list = mutableListOf<RecyclerViewModel>()
        val adapter = RecyclerViewAdapter(list)
        recyclerView.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}