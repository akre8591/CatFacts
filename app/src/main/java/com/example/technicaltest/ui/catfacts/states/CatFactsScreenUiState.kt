package com.example.technicaltest.ui.catfacts.states

import com.example.technicaltest.data.domain.model.CatFactsModel

sealed interface CatFactsScreenUiState {
    data object Loading : CatFactsScreenUiState
    data class Success(
        val catFacts: List<CatFactsModel>
    ) : CatFactsScreenUiState

    data class Error(val message: String) : CatFactsScreenUiState
}
