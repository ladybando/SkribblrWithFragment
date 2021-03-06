package com.example.android.testlistwithfragment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.testlistwithfragment.databinding.FragmentListItemBinding

class ListItemFragment : Fragment() {
    private var _binding: FragmentListItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View {
        // Inflate the layout for this fragment
        _binding= FragmentListItemBinding.inflate(inflater, container, false)
        return binding.root
    }

}