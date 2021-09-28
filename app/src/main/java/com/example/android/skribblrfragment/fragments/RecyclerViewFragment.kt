package com.example.android.skribblrfragment.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.skribblrfragment.R
import com.example.android.skribblrfragment.adapter.TaskViewAdapter
import com.example.android.skribblrfragment.databinding.FragmentRecyclerViewBinding
import com.example.android.skribblrfragment.model.SharedViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RecyclerViewFragment : Fragment(), TaskViewAdapter.Listener {

    private var _binding : FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: TaskViewAdapter
    private val viewModel: SharedViewModel by viewModels()
    private var position = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        adapter = TaskViewAdapter(this, this.requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    }

    override fun onTaskClicked(index: Int) {
        TODO("not yet implemented")
        /* val intent = Intent(this, ListOfItemsActivity::class.java)
         intent.putExtra(INDEX, index)
         intent.putExtra(INTENT_DATA_NAME, viewModel.taskList[index])
         position = index
         resultLauncher.launch(intent)*/
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
                position = index
                viewModel.taskList.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
            .show()
    }
}