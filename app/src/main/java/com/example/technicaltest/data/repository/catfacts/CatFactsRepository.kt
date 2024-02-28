package com.example.technicaltest.data.repository.catfacts

import com.example.technicaltest.ui.components.catfacts.CatFactsScreenUiState
import kotlinx.coroutines.flow.Flow

interface CatFactsRepository {
    fun getCatFacts(): Flow<CatFactsScreenUiState>
}
