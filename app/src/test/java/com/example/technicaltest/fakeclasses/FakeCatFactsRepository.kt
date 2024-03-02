package com.example.technicaltest.fakeclasses

import com.example.technicaltest.data.repository.catfacts.CatFactsRepository
import com.example.technicaltest.ui.components.catfacts.CatFactsScreenUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCatFactsRepository : CatFactsRepository {
    val catFacts = MutableStateFlow<CatFactsScreenUiState>(CatFactsScreenUiState.Loading)
    override fun getCatFacts(): Flow<CatFactsScreenUiState> = catFacts
}
