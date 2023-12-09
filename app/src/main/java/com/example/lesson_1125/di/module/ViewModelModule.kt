package com.example.lesson_1125.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_1125.di.viewModel.ViewModelFactory
import com.example.lesson_1125.di.viewModel.ViewModelKey
import com.example.lesson_1125.presentation.viewMode.HomeViewModel
import com.example.lesson_1125.presentation.viewMode.ModifyTaskViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ModifyTaskViewModel::class)
    fun bindModifyTaskViewModel(viewModel: ModifyTaskViewModel): ViewModel
}
