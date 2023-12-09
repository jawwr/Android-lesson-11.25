package com.example.lesson_1125.di.module

import android.app.Application
import android.content.Context
import com.example.lesson_1125.data.repository.TasksRepository
import com.example.lesson_1125.data.repository.TasksRepositoryImpl
import com.example.lesson_1125.domain.DeleteTasksUseCase
import com.example.lesson_1125.domain.DeleteTasksUseCaseImpl
import com.example.lesson_1125.domain.SubscribeTasksOrderedUseCase
import com.example.lesson_1125.domain.SubscribeTasksOrderedUseCaseImpl
import com.example.lesson_1125.domain.UpsertTasksUseCase
import com.example.lesson_1125.domain.UpsertTasksUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AppBindsModule {
    @Binds
    @Singleton
    fun bindTasksRepository(repository: TasksRepositoryImpl): TasksRepository

    @Binds
    fun bindSubscribeTasksUseCase(useCase: SubscribeTasksOrderedUseCaseImpl): SubscribeTasksOrderedUseCase

    @Binds
    fun bindUpsertTasksUseCase(useCase: UpsertTasksUseCaseImpl): UpsertTasksUseCase

    @Binds
    fun bindDeleteTasksUseCase(useCase: DeleteTasksUseCaseImpl): DeleteTasksUseCase

    companion object {
        @Provides
        fun provideContext(app: Application): Context = app.applicationContext
    }
}
