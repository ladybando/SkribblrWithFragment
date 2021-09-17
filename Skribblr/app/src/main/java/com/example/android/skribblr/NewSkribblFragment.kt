package com.example.android.skribblr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.example.android.skribblr.data.DataSource
import com.example.android.skribblr.databinding.ActivityMainBinding
import com.example.android.skribblr.databinding.FragmentNewSkribblBinding
import com.example.android.skribblr.model.ViewModel


class NewSkribblFragment : Fragment(){
    // this fragment should only show the add new skribble text view and then redirect to list view

    private var _binding: FragmentNewSkribblBinding? = null
    private val binding get() = _binding!!
    private lateinit var constraintView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewSkribblBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        constraintView=binding.constraintNewSkribbl

        val inputString=binding.skribblTextView.text.toString()
        val newSkribble= mutableListOf<ViewModel>()
        binding.submitButton.setOnClickListener {
            newSkribble.add(ViewModel(inputString))
            val intent=Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}