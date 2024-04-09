package com.example.technicaltest.ui.catfacts.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltest.data.domain.interactor.GetCatFactDetailsUseCase
import com.example.technicaltest.navigation.NavigationDestinations.CAT_FACT_ID
import com.example.technicaltest.ui.catfacts.states.CatFactDetailsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CatFactDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCatFactDetailsUseCase: GetCatFactDetailsUseCase,
) : ViewModel() {

    private val catFactId: String? = savedStateHandle[CAT_FACT_ID]

    val uiState: StateFlow<CatFactDetailsScreenUiState> =
        getCatFactDetailsUseCase(catFactId.orEmpty()).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = CatFactDetailsScreenUiState.Loading
        )
}
