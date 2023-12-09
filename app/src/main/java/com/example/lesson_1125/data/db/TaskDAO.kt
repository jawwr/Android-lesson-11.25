package com.example.lesson_1125.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.lesson_1125.data.db.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {
    @Upsert
    suspend fun upsertTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query(
        """
        SELECT *
        FROM tasks
        ORDER BY endTime
    """
    )
    fun getTasksOrderedByEndTime(): Flow<List<TaskEntity>>
}
