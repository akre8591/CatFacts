package com.example.technicaltest.di.services

import com.example.technicaltest.remote.api.CatFactsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object CatFactsServiceModule {

    @Provides
    fun providesCatFactsServices(
        retrofit: Retrofit
    ) = retrofit.create<CatFactsService>()
}
