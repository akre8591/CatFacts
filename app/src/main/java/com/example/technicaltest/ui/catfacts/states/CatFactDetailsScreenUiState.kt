package com.example.technicaltest.ui.catfacts.states

import com.example.technicaltest.data.domain.model.CatFactsModel

sealed interface CatFactDetailsScreenUiState {
    data object Loading : CatFactDetailsScreenUiState
    data class Success(val catFact: CatFactsModel) : CatFactDetailsScreenUiState
    data class Error(val message: String) : CatFactDetailsScreenUiState
}
