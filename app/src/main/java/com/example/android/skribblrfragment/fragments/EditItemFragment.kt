package com.example.android.skribblrfragment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.skribblrfragment.R
import com.example.android.skribblrfragment.databinding.FragmentEditItemBinding

class EditItemFragment : Fragment() {
    private var _binding: FragmentEditItemBinding? = null
    private val binding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditItemBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editUserInput = binding.editText.text.toString()


    }

    fun newInstance(param1: String, param2: String) =
        EditItemFragment().apply {
            arguments = Bundle().apply {
                TODO("not yet implemented")
            }
        }
}