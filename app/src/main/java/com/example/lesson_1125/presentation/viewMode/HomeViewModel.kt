package com.example.lesson_1125.presentation.viewMode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_1125.data.db.TaskState
import com.example.lesson_1125.data.model.Task
import com.example.lesson_1125.domain.SubscribeTasksOrderedUseCase
import com.example.lesson_1125.domain.UpsertTasksUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val subscribeTasksOrderedUseCase: SubscribeTasksOrderedUseCase,
    private val upsertTasksUseCase: UpsertTasksUseCase
) : ViewModel() {
    fun changeTaskState(task: Task, index: Int) {
        val taskState = TaskState.values()[index]
        val copy = task.copy(
            state = taskState
        )
        viewModelScope.launch {
            upsertTasksUseCase(copy)
        }
    }

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    init {
        viewModelScope.launch {
            subscribeTasksOrderedUseCase().collect {
                _tasks.postValue(it)
            }
        }
    }
}
