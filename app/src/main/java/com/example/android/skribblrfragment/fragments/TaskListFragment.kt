package com.example.android.skribblrfragment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.skribblrfragment.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment() {

    private var _binding : FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userInput = binding.newItemEditText
        // val dataFromMain = intent.getStringExtra(INTENT_DATA_NAME)
        // set text from main to editText field
        //userInput.setText(dataFromMain)

        binding.submitNewButton.setOnClickListener {
            TODO("not yet implemented")
        }
    }

}