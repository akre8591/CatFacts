package com.example.technicaltest.di.binds

import com.example.technicaltest.utils.AppDispatcher
import com.example.technicaltest.utils.AppDispatcherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindAppDispatcher {

    @Binds
    @Singleton
    abstract fun bindAppDispatcher(appDispatcherImpl: AppDispatcherImpl): AppDispatcher
}
