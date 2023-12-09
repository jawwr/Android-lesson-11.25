package com.example.lesson_1125.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.lesson_1125.data.db.TaskDAO
import com.example.lesson_1125.data.db.TaskDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): TaskDatabase = Room.databaseBuilder(
        context = context,
        TaskDatabase::class.java,
        "task.db"
    ).build()

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDAO =
        db.taskDAO
}
