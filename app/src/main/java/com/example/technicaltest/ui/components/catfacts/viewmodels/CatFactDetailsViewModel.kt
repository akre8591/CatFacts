package com.example.technicaltest.ui.components.catfacts.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.technicaltest.navigation.NavigationDestinations.CAT_FACT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatFactDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val catFactId: String? = savedStateHandle[CAT_FACT_ID]
}
