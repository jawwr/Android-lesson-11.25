package com.example.lesson_1125.domain

import com.example.lesson_1125.data.model.Task
import com.example.lesson_1125.data.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SubscribeTasksOrderedUseCase {
    operator fun invoke(): Flow<List<Task>>
}

class SubscribeTasksOrderedUseCaseImpl @Inject constructor(
    private val tasksRepository: TasksRepository
) : SubscribeTasksOrderedUseCase {
    override fun invoke(): Flow<List<Task>> = tasksRepository.taskOrdered
}
