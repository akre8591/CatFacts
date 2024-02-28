package com.example.technicaltest.di.modules

import com.example.technicaltest.local.dao.CatFactsDao
import com.example.technicaltest.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun provideCatFactsDao(appDatabase: AppDatabase): CatFactsDao =
        appDatabase.catFacts()
}
