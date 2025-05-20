package com.aliihsan.todolistapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliihsan.todolistapp.R
import com.aliihsan.todolistapp.databinding.FragmentTaskListPageBinding
import com.aliihsan.todolistapp.ui.adapter.TaskListAdapter
import com.aliihsan.todolistapp.ui.viewmodel.TaskListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListPage : Fragment() {

    private lateinit var binding: FragmentTaskListPageBinding
    private lateinit var viewModel: TaskListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: TaskListViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListPageBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_taskListPage_to_addTaskBottomSheetFragment)
        }

        binding.searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Harf girişi ve çıkışı olduğunda çalışır
                viewModel.searchTask(query)
                return true

            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Arama butonuna basılınca
                viewModel.searchTask(newText)
                return true

            }
        })

        viewModel.taskList.observe(viewLifecycleOwner) {
            val adapter = TaskListAdapter(requireContext(), it, viewModel)
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        }


        return binding.root

    }

}