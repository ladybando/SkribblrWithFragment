package com.example.android.skribblrfragment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.skribblrfragment.databinding.FragmentTaskListBinding
import com.example.android.skribblrfragment.model.SharedViewModel

class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!
    private val args: TaskListFragmentArgs by navArgs()
    private val viewModel :SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val editTextView = binding.taskListEditText
        val userInput = args.newUserInput
        editTextView.setText(userInput)

        binding.submitNewButton.setOnClickListener {
            val newInput = editTextView.text.toString()
            val isInEditingMode = args.isEditingTask
            // Only add the input to the existing list if we are not in editing mode. If we are in
            // editing mode, the edited task is passed by to RecyclerViewFragment and handled in
            // onResume()
            if (!isInEditingMode) {
                viewModel.taskList.add(newInput)
            }
            val action =
                TaskListFragmentDirections.actionTaskListFragmentToRecyclerViewFragment(newInput)
            findNavController().navigate(action)
        }
    }
}