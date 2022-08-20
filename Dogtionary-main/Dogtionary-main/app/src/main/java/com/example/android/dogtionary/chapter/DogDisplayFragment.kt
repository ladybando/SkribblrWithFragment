package com.example.android.dogtionary.chapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.dogtionary.R
import com.example.android.dogtionary.application.DogImageApplication
import com.example.android.dogtionary.databinding.FragmentDogDisplayBinding


class DogDisplayFragment : Fragment() {

    private val viewModel: DogViewModel by activityViewModels{
        DogViewModelFactory(
            (activity?.application as DogImageApplication).database
                .dogDao()
        )
    }
    private lateinit var button: Button
    private lateinit var previousButton: Button
    private var _binding: FragmentDogDisplayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dog_display, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        showRandomPhoto()
        return binding.root
    }

    private fun showRandomPhoto() {
        button = binding.submitButton
        button.setOnClickListener {
            viewModel.getNewPhoto()
        }
    }

    private fun showLastImage(){
        previousButton = binding.previousButton
        previousButton.setOnClickListener {
            //show final image after iteration
            val allImages = viewModel.getImageList()
            Log.d("DisplayFrag", allImages.toString())
        }
    }
}