package com.example.technicaltest.ui.catfacts.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.technicaltest.ui.catfacts.states.CatFactDetailsScreenUiState
import com.example.technicaltest.ui.catfacts.viewmodels.CatFactDetailsViewModel
import com.example.technicaltest.ui.components.CatFactsError
import com.example.technicaltest.ui.components.CatFactsProgress
import com.example.technicaltest.utils.TestConstants

@Composable
fun CatFactDetailsRoute(
    viewModel: CatFactDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CatFactDetailsScreen(uiState = uiState)
}

@Composable
fun CatFactDetailsScreen(
    uiState: CatFactDetailsScreenUiState,
) {
    when (uiState) {
        is CatFactDetailsScreenUiState.Loading -> {
            CatFactsProgress(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(TestConstants.CAT_FACT_DETAILS_LOADING),
            )
        }

        is CatFactDetailsScreenUiState.Success -> {
            Column(
                modifier = Modifier
                    .testTag(TestConstants.CAT_FACT_DETAILS)
                    .padding(vertical = 16.dp, horizontal = 24.dp)
            ) {
                androidx.compose.material3.Text(
                    text = uiState.catFact.type,
                    fontSize = 24.sp
                )
                androidx.compose.material3.Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = uiState.catFact.text,
                    fontSize = 16.sp
                )
            }
        }

        is CatFactDetailsScreenUiState.Error -> {
            CatFactsError(
                modifier = Modifier
                    .testTag(TestConstants.CAT_FACT_DETAILS_ERROR)
                    .fillMaxSize(),
                message = uiState.message
            )
        }
    }
}
