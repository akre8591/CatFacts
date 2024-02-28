package com.example.technicaltest.ui.components.catfacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltest.data.repository.catfacts.CatFactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CatFactsViewModel @Inject constructor(
    catFactsRepository: CatFactsRepository
) : ViewModel() {

    val uiState: StateFlow<CatFactsScreenUiState> = catFactsRepository.getCatFacts().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = CatFactsScreenUiState.Loading
    )
}
