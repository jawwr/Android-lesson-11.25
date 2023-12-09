package com.example.lesson_1125.presentation.viewMode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_1125.data.model.Task
import com.example.lesson_1125.domain.UpsertTasksUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModifyTaskViewModel @Inject constructor(
    private val upsertTasksUseCase: UpsertTasksUseCase
) : ViewModel() {
    private var task = Task(
        title = "",
        description = "",
        endTime = -1,
        startTime = -1
    )

    fun validateForm(title: String, desc: String): Boolean {
        if (title.trim().isEmpty() || desc.trim().isEmpty()) {
            return true
        }
        task = task.copy(
            title = title.trim(),
            description = desc.trim()
        )
        return task.startTime != -1L && task.endTime != -1L
    }

    fun createTask() {
        viewModelScope.launch {
            upsertTasksUseCase(task)
        }
    }

    fun saveTimeInterval(startTime: Long, endTime: Long) {
        task = task.copy(
            startTime = startTime,
            endTime = endTime
        )
    }
}
