package com.example.lesson_1125.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lesson_1125.data.db.model.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDAO: TaskDAO
}
