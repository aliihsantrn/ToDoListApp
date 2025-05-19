package com.aliihsan.todolistapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliihsan.todolistapp.R
import com.aliihsan.todolistapp.databinding.FragmentAddTaskPageBinding

class AddTaskPage : Fragment() {

    private lateinit var binding: FragmentAddTaskPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}