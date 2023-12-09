package com.example.lesson_1125.domain

import com.example.lesson_1125.data.model.Task
import com.example.lesson_1125.data.repository.TasksRepository
import javax.inject.Inject

interface DeleteTasksUseCase {
    suspend operator fun invoke(task: Task)
}

class DeleteTasksUseCaseImpl @Inject constructor(
    private val tasksRepository: TasksRepository
) : DeleteTasksUseCase {
    override suspend fun invoke(task: Task) = tasksRepository.deleteTask(task)
}
