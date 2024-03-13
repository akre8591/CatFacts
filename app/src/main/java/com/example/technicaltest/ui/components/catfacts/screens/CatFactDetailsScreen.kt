package com.example.technicaltest.ui.components.catfacts.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.technicaltest.ui.components.catfacts.viewmodels.CatFactDetailsViewModel

@Composable
fun CatFactDetailsRoute(
    viewModel: CatFactDetailsViewModel = hiltViewModel()
) {
    CatFactDetailsScreen(viewModel.catFactId.orEmpty())
}

@Composable
fun CatFactDetailsScreen(
    id: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = id)
    }
}
