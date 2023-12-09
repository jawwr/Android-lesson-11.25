package com.example.lesson_1125.di

import android.content.Context
import com.example.lesson_1125.App

val Context.appComponent: AppComponent
    get() = when(this) {
        is App -> this.appComponent
        else -> applicationContext.appComponent
    }
