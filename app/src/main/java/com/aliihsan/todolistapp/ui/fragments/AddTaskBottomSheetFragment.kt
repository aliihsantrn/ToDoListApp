package com.aliihsan.todolistapp.ui.fragments

import android.app.Dialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import com.aliihsan.todolistapp.R
import com.aliihsan.todolistapp.databinding.FragmentAddTaskPageBinding
import com.aliihsan.todolistapp.ui.viewmodel.AddTaskViewModel
import com.aliihsan.todolistapp.ui.viewmodel.TaskDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddTaskPageBinding
    private lateinit var viewModel: AddTaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AddTaskViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskPageBinding.inflate(inflater, container, false)

        binding.addTaskBtn.setOnClickListener {
            val tittle = binding.taskTittleText.text.toString().trim()
            val description = binding.taskDescriptionText.text.toString().trim()

            val checkedButtonId = binding.toggleButton.checkedButtonId

            val taskdate = when (checkedButtonId) {
                R.id.button1 -> binding.button1.text.toString() // Today
                R.id.button2 -> binding.button2.text.toString() // Tomorrow
                R.id.button3 -> binding.button3.text.toString() // This Weekend
                else -> "Today"
            }

            // Boş alan kontrolü
            if (tittle.isEmpty() || description.isEmpty() || taskdate.isEmpty()) {
                getDialog() // Hatalı giriş uyarısı
                return@setOnClickListener
            }

            viewModel.addContact(tittle, description, "Today")

            binding.taskTittleText.text?.clear()
            binding.taskDescriptionText.text?.clear()
            binding.taskTittleText.clearFocus()
            binding.taskDescriptionText.clearFocus()

            val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            Snackbar.make(binding.root, "Contact added", Snackbar.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun getDialog(): Dialog? {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage("Please fill all fields")
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}