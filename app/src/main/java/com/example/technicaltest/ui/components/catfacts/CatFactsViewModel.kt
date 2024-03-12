package com.example.technicaltest.ui.components.catfacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltest.data.domain.interactor.GetCatFactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class CatFactsViewModel @Inject constructor(
    getCatFactsUseCase: GetCatFactsUseCase
) : ViewModel() {

    private val forceToRefresh: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<CatFactsScreenUiState> = forceToRefresh.flatMapLatest {
        getCatFactsUseCase()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = CatFactsScreenUiState.Loading
    )

    fun refresh() {
        isRefreshing.value = true
        forceToRefresh.value = !forceToRefresh.value
        isRefreshing.value = false
    }
}
