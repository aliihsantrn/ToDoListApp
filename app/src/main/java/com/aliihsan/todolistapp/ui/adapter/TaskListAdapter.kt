package com.aliihsan.todolistapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aliihsan.todolistapp.data.entity.TaskModel
import com.aliihsan.todolistapp.databinding.TaskCardDesignBinding
import com.aliihsan.todolistapp.ui.fragments.TaskListPageDirections
import com.aliihsan.todolistapp.ui.viewmodel.TaskListViewModel
import com.google.android.material.snackbar.Snackbar

class TaskListAdapter(
    private val mContext: Context,
    private val taskList: List<TaskModel>,
    private val viewModel: TaskListViewModel
) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(var binding: TaskCardDesignBinding)
        : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val design = TaskCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return TaskViewHolder(design)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        val design = holder.binding
        design.txtTaskTittle.text = task.taskTittle
        design.txtTaskDate.text = task.taskDate
        design.cardView.setOnClickListener {
            val sendContact = TaskListPageDirections.actionTaskListPageToTaskDetailPage(task)
            Navigation.findNavController(it).navigate(sendContact)
        }
        design.closeBtn.setOnClickListener {
            Snackbar.make(it, "Should ${task.taskTittle} be deleted? ", Snackbar.LENGTH_LONG)
                .setAction("YES") {
                    viewModel.deleteTask(task.id)
                    Snackbar.make(it, "${task.taskTittle} is deleted", Snackbar.LENGTH_LONG).show()
                }.show()

        }
    }

}