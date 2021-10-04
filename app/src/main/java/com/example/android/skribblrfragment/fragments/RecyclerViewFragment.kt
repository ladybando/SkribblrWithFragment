package com.example.android.skribblrfragment.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.skribblrfragment.R
import com.example.android.skribblrfragment.adapter.TaskViewAdapter
import com.example.android.skribblrfragment.databinding.FragmentRecyclerViewBinding
import com.example.android.skribblrfragment.databinding.ItemLayoutBinding
import com.example.android.skribblrfragment.model.SharedViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RecyclerViewFragment : Fragment(), TaskViewAdapter.Listener {

    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskViewAdapter

    private val viewModel: SharedViewModel by activityViewModels()
    private val args: RecyclerViewFragmentArgs by navArgs()

    private var input: String? = " "

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        adapter = TaskViewAdapter(this, this.requireContext(), viewModel.taskList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.submitButton.setOnClickListener {
            val action =
                RecyclerViewFragmentDirections.actionRecyclerViewFragmentToTaskListFragment(input!!)
            findNavController().navigate(action)
        }
    }

    //i've tried adding it to onViewCreated, creating a different function and then calling it
    //and then onResume()
    //the task is not edited. it's just added
    override fun onResume() {
        super.onResume()
        val editedInput = args.editUserInput
        val position = viewModel.listPosition
        if (position != -1) {
            viewModel.taskList[position] = editedInput!!
            adapter.notifyItemChanged(position)
            // Reset position to -1 so adding a new task doesn't change previously edited tasks
            viewModel.listPosition = -1
        }
    }

    override fun onTaskClicked(index: Int) {
        viewModel.listPosition = index
        val newUserInput = viewModel.taskList[viewModel.listPosition]
        val action =
            RecyclerViewFragmentDirections.actionRecyclerViewFragmentToTaskListFragment(newUserInput, true)
        adapter.notifyDataSetChanged()
        findNavController().navigate(action)

    }

    override fun onLongTaskClicked(context: Context, index: Int) {
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.title)
            .setMessage(R.string.affirmation)
            .setCancelable(false)
            .setNegativeButton(R.string.decline) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.accept) { _, _ ->
                viewModel.listPosition = index
                viewModel.taskList.removeAt(viewModel.listPosition)
                adapter.notifyItemRemoved(viewModel.listPosition)
            }
            .show()
    }
}
