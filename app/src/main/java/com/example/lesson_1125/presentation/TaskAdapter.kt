package com.example.lesson_1125.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_1125.DATE_FORMAT
import com.example.lesson_1125.data.model.Task
import com.example.lesson_1125.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter(
    private val onTaskStateClick: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffUtil()) {
    class TaskViewHolder(
        private val onTaskStateClick: (Task) -> Unit,
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) = with(binding) {
            val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

            itemTaskTitle.text = task.title
            itemTaskDescription.text = task.description
            itemTaskEndTime.text = formatter.format(task.endTime)
            itemTaskStartTime.text = formatter.format(task.startTime)
            itemTaskStatus.text = task.state.name
            itemTaskStatus.setOnClickListener {
                onTaskStateClick(task)
            }
        }
    }

    class TaskDiffUtil : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = newItem == oldItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(onTaskStateClick, binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
