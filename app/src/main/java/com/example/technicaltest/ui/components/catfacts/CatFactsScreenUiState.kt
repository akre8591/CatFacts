package com.example.technicaltest.ui.components.catfacts

import com.example.technicaltest.data.domain.model.CatFactsModel

sealed interface CatFactsScreenUiState {
    data object Loading : CatFactsScreenUiState
    data class Success(
        val funCats: List<CatFactsModel>
    ) : CatFactsScreenUiState

    data class Error(val message: String) : CatFactsScreenUiState
}
