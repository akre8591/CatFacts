package com.example.technicaltest.di.binds

import com.example.technicaltest.data.repository.catfacts.CatFactsRepository
import com.example.technicaltest.data.repository.catfacts.CatFactsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindCatFactsRepository {

    @Binds
    abstract fun bindCatFactsRepository(catFactsRepositoryImpl: CatFactsRepositoryImpl): CatFactsRepository
}
