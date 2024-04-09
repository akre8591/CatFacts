package com.example.technicaltest.data.domain.interactor

import com.example.technicaltest.data.repository.catfacts.CatFactsRepository
import com.example.technicaltest.ui.catfacts.states.CatFactDetailsScreenUiState
import com.example.technicaltest.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCatFactDetailsUseCase @Inject constructor(
    private val catFactsRepository: CatFactsRepository,
) {
    operator fun invoke(id: String): Flow<CatFactDetailsScreenUiState> =
        catFactsRepository.getCatFactDetails(id = id).map { catFactDetails ->
            when (catFactDetails) {
                is DataState.Success -> CatFactDetailsScreenUiState.Success(catFact = catFactDetails.data)
                is DataState.Failure -> CatFactDetailsScreenUiState.Error(message = catFactDetails.e.message.orEmpty())
                else -> CatFactDetailsScreenUiState.Loading
            }
        }
}
