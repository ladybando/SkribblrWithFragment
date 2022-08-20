package com.example.android.dogtionary.chapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.android.dogtionary.databinding.FragmentPreviousImageBinding

class PreviousImageFragment : Fragment() {
    private var _binding: FragmentPreviousImageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPreviousImageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     //   val lastImage = viewModel.getLastImage()
     //   Log.d("PreviousImage", lastImage)
    }
}