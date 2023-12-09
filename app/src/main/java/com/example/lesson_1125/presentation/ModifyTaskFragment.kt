package com.example.lesson_1125.presentation

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_1125.DATE_FORMAT
import com.example.lesson_1125.R
import com.example.lesson_1125.SEC_TO_MS
import com.example.lesson_1125.WEEK
import com.example.lesson_1125.databinding.FragmentModifyTaskBinding
import com.example.lesson_1125.di.viewModel.ViewModelFactory
import com.example.lesson_1125.di.appComponent
import com.example.lesson_1125.presentation.viewMode.ModifyTaskViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ModifyTaskFragment : Fragment(R.layout.fragment_modify_task) {

    private val binding: FragmentModifyTaskBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ModifyTaskViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
        .setTitleText("Select dates")
        .setSelection(
            androidx.core.util.Pair(
                MaterialDatePicker.todayInUtcMilliseconds(),
                MaterialDatePicker.todayInUtcMilliseconds() + WEEK * SEC_TO_MS
            )
        )
        .build()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateRangePicker.addOnPositiveButtonClickListener {
            onDateIntervalPicked(it)
        }
        binding.newTaskButtonPickDate.setOnClickListener {
            dateRangePicker.show(childFragmentManager, dateRangePicker::class.java.simpleName)
        }
        binding.newTaskToolbar.setOnMenuItemClickListener {
            onFormReady()
        }
        binding.newTaskToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onFormReady(): Boolean {
        val isFormValid = viewModel.validateForm(
            binding.newTaskTitle.editText?.text.toString(),
            binding.newTaskDescription.editText?.text.toString()
        )
        if (isFormValid) {
            viewModel.createTask()
            findNavController().popBackStack()
        } else {
            Snackbar
                .make(binding.root, "Fill out all the gaps", Snackbar.LENGTH_LONG)
                .show()
        }
        return isFormValid
    }

    private fun onDateIntervalPicked(it: Pair<Long, Long>) {
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        viewModel.saveTimeInterval(it.first, it.second)
        val shown = SpannableStringBuilder()
            .append(formatter.format(Date(it.first)))
            .append(" to ")
            .append(formatter.format(Date(it.second)))
        binding.newTaskDateInterval.editText?.text = shown
    }
}
