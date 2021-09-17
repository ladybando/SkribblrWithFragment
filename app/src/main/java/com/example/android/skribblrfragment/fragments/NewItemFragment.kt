package com.example.android.skribblrfragment.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.android.skribblrfragment.R
import com.example.android.skribblrfragment.adapter.RecyclerViewAdapter
import com.example.android.skribblrfragment.databinding.ActivityMainBinding
import com.example.android.skribblrfragment.databinding.FragmentNewItemBinding
import com.example.android.skribblrfragment.model.SharedViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

const val INTENT_DATA_NAME = "input"

private var _binding: FragmentNewItemBinding? = null
private val binding get() = _binding!!

private var _mainBinding: ActivityMainBinding? = null
private val mainBinding get() = _mainBinding!!

private lateinit var recyclerView: RecyclerView

class NewItemFragment : Fragment() {
    private val viewModel: SharedViewModel by viewModels()

    private val high: String = "HIGH"
    private val medium: String = "MEDIUM"
    private val low: String = "LOW"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?, ): View {
        _binding = FragmentNewItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo initialize layout variables if needed

        createNewSkribbl()
    }

    private fun createNewSkribbl() {
        binding.submitButton.setOnClickListener {
            val userInput = binding.newEditText.text.toString()
            //create new intent to pass data
            val intent = Intent()
            //passing data back to [MainActivity]
            intent.putExtra(INTENT_DATA_NAME, userInput)

            //get access to recycler view
            _mainBinding = ActivityMainBinding.inflate(layoutInflater)
            recyclerView = mainBinding.recyclerView
            val list = mutableListOf<SharedViewModel>()
            val adapter = RecyclerViewAdapter(list)
            recyclerView.adapter = adapter
            //add user input to [DataSource] MutableList
            list.add(SharedViewModel(userInput))
            //todo find a setresult() method for fragment
            //update adapter to display new item inserted
            adapter.notifyItemInserted(0)
            //todo find a finish() method for fragment
        }
    }

    fun deleteTaskDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.title)
            .setMessage(R.string.affirmation)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.confirmation)) { _, _ ->
                viewModel.removeTask()
            }
            .setNegativeButton(getString(R.string.denial)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

//set urgency color based on user input
/*    fun setUrgencyTextField(urgency: Boolean){
        when (urgency){
           "HIGH"-> binding.newEditText.compoundDrawables = (R.id.high)
        }
    }*/
}