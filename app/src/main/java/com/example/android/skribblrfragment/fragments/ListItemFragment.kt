package com.example.android.skribblrfragment.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.skribblrfragment.R
import com.example.android.skribblrfragment.adapter.RecyclerViewAdapter
import com.example.android.skribblrfragment.databinding.ActivityMainBinding
import com.example.android.skribblrfragment.databinding.FragmentListItemBinding
import com.example.android.skribblrfragment.model.SharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListItemFragment : Fragment() {
    private var list = mutableListOf<SharedViewModel>()
    private val adapter = RecyclerViewAdapter(list)
    private lateinit var recyclerView : RecyclerView
    private val viewModel: SharedViewModel by viewModels()

    private var _binding: FragmentListItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        _binding = FragmentListItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mbinding=ActivityMainBinding.inflate(layoutInflater)
        recyclerView = mbinding.recyclerView
        //position=SparseBooleanArray   = list.checkedItemPositions
        //set [RecyclerView] layout to grid
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter

        binding.listItemEditText.setOnClickListener{
            viewModel.taskList.remove(viewModel.userInput)
            adapter.notifyItemRemoved(0)
        }
    }
}