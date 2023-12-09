package com.example.lesson_1125.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lesson_1125.data.db.TaskState
import com.example.lesson_1125.data.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val state: TaskState = TaskState.TODO
) {
    fun toTask(): Task = Task(id, title, description, startTime, endTime, state)
}
