package com.example.lesson_1125.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.lesson_1125.data.db.TaskDAO
import com.example.lesson_1125.data.db.TaskDatabase
import com.example.lesson_1125.data.db.migration.TaskStateMigration
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideMigrations() = arrayOf<Migration>(
        TaskStateMigration()
    )

    @Provides
    fun provideDatabase(context: Context, migrations: Array<Migration>): TaskDatabase =
        Room.databaseBuilder(
            context = context,
            TaskDatabase::class.java,
            "task.db"
        ).addMigrations(*migrations)
            .build()

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDAO =
        db.taskDAO
}
