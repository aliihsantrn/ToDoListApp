package com.aliihsan.todolistapp.ui.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aliihsan.todolistapp.R
import com.aliihsan.todolistapp.databinding.FragmentTaskDetailPageBinding
import com.aliihsan.todolistapp.ui.viewmodel.TaskDetailViewModel
import com.aliihsan.todolistapp.ui.viewmodel.TaskListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailPage : Fragment() {

    private lateinit var binding: FragmentTaskDetailPageBinding
    private lateinit var viewModel: TaskDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: TaskDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskDetailPageBinding.inflate(inflater, container, false)

        val bundle: TaskDetailPageArgs by navArgs()
        val task = bundle.task
        binding.taskTittleText.setText(task.taskTittle)
        binding.taskDescriptionText.setText(task.taskDescription)

        binding.updateTaskBtn.setOnClickListener {
            val title = binding.taskTittleText.text.toString()
            val description = binding.taskDescriptionText.text.toString()

            val checkedButtonId = binding.toggleButton.checkedButtonId

            val taskdate = when (checkedButtonId) {
                R.id.button1 -> binding.button1.text.toString() // Today
                R.id.button2 -> binding.button2.text.toString() // Tomorrow
                R.id.button3 -> binding.button3.text.toString() // This Weekend
                else -> "Today"
            }
            viewModel.updateContact(task.id, title, description, taskdate)

            // Focus kaldır
            binding.taskTittleText.clearFocus()
            binding.taskDescriptionText.clearFocus()

            val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            Snackbar.make(binding.root, "Contact updated", Snackbar.LENGTH_SHORT).show()
        }
        return binding.root

    }

}