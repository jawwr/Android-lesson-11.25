package com.example.lesson_1125.data.model

import com.example.lesson_1125.data.db.TaskState
import com.example.lesson_1125.data.db.model.TaskEntity

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val state: TaskState
) {
    fun toTaskEntity(): TaskEntity = TaskEntity(id, title, description, startTime, endTime, state)
}
