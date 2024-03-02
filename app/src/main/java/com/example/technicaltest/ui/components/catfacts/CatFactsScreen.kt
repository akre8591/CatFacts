package com.example.technicaltest.ui.components.catfacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.technicaltest.utils.TestConstants
import com.example.technicaltest.utils.navigateTo

@Composable
fun CatFactsRoute(
    navController: NavHostController,
    viewModel: CatFactsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CatFactsScreen(
        navigateTo = navController::navigateTo,
        uiState = uiState
    )
}

@Composable
fun CatFactsScreen(
    navigateTo: (String) -> Unit,
    uiState: CatFactsScreenUiState
) {
    when (uiState) {
        is CatFactsScreenUiState.Loading -> {
            Box(
                modifier = Modifier
                    .testTag(TestConstants.CAT_FACTS_LOADING)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is CatFactsScreenUiState.Success -> {
            CatFactsListComponent(catFactsList = uiState.catFacts)
        }

        is CatFactsScreenUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.message)
            }
        }
    }
}
