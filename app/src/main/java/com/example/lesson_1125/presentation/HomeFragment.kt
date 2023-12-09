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
import com.example.lesson_1125.databinding.FragmentHomeBinding
import com.example.lesson_1125.di.viewModel.ViewModelFactory
import com.example.lesson_1125.di.appComponent
import com.example.lesson_1125.presentation.viewMode.HomeViewModel
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val adapter = TaskAdapter()

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

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}
