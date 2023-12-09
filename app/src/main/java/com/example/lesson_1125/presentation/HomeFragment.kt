package com.example.lesson_1125.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_1125.R
import com.example.lesson_1125.data.db.TaskState
import com.example.lesson_1125.data.model.Task
import com.example.lesson_1125.databinding.FragmentHomeBinding
import com.example.lesson_1125.di.viewModel.ViewModelFactory
import com.example.lesson_1125.di.appComponent
import com.example.lesson_1125.presentation.viewMode.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val adapter = TaskAdapter(::onTaskClickListener)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.tasksRecycler) {
            adapter = this@HomeFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.buttonCreateTask.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToModifyTaskFragment()
            findNavController().navigate(direction)
        }
    }

    private fun onTaskClickListener(task: Task) {
        val states = TaskState.values().map { it.name }.toTypedArray()
        var index = -1;

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Select new state")
            .setNeutralButton("Cancel") { _, _ -> }
            .setPositiveButton("Ok") { _, _ ->
                viewModel.changeTaskState(task, index)
            }.setSingleChoiceItems(states, task.state.ordinal) { _, which ->
                index = which
            }.show()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}
