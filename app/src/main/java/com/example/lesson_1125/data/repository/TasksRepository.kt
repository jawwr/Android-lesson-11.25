package com.example.lesson_1125.data.repository

import com.example.lesson_1125.data.model.Task
import com.example.lesson_1125.data.db.TaskDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface TasksRepository {
    val taskOrdered: Flow<List<Task>>
    suspend fun upset(task: Task)
    suspend fun deleteTask(task: Task)
}

class TasksRepositoryImpl @Inject constructor(
    private val dao: TaskDAO
) : TasksRepository {
    override val taskOrdered: Flow<List<Task>>
        get() = dao.getTasksOrderedByEndTime().map {
            it.map { it.toTask() }
        }

    override suspend fun upset(task: Task) {
        dao.upsertTask(task.toTaskEntity())
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task.toTaskEntity())
    }
}
