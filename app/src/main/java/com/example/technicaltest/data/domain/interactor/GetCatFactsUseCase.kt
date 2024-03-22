package com.example.technicaltest.data.domain.interactor

import com.example.technicaltest.data.repository.catfacts.CatFactsRepository
import com.example.technicaltest.ui.catfacts.states.CatFactsScreenUiState
import com.example.technicaltest.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCatFactsUseCase @Inject constructor(
    private val catFactsRepository: CatFactsRepository
) {
    operator fun invoke(): Flow<CatFactsScreenUiState> =
        catFactsRepository.getCatFacts().map { catFacts ->
            when (catFacts) {
                is DataState.Success -> CatFactsScreenUiState.Success(catFacts.data)

                is DataState.Failure -> CatFactsScreenUiState.Error(catFacts.e.message.orEmpty())
                else -> CatFactsScreenUiState.Loading
            }
        }
}
