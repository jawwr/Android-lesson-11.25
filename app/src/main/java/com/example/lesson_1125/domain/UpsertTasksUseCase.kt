package com.example.lesson_1125.domain

import com.example.lesson_1125.data.model.Task
import com.example.lesson_1125.data.repository.TasksRepository
import javax.inject.Inject

interface UpsertTasksUseCase {
    suspend operator fun invoke(task: Task)
}

class UpsertTasksUseCaseImpl @Inject constructor(
    private val tasksRepository: TasksRepository
) : UpsertTasksUseCase {
    override suspend fun invoke(task: Task) = tasksRepository.upset(task)
}
