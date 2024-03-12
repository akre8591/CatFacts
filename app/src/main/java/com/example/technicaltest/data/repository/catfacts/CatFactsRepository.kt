package com.example.technicaltest.data.repository.catfacts

import com.example.technicaltest.data.domain.model.CatFactsModel
import com.example.technicaltest.utils.DataState
import kotlinx.coroutines.flow.Flow

interface CatFactsRepository {
    fun getCatFacts(): Flow<DataState<List<CatFactsModel>>>
}
