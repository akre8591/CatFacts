package com.example.technicaltest.ui.components.catfacts.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.technicaltest.ui.components.catfacts.states.CatFactsScreenUiState
import com.example.technicaltest.ui.components.catfacts.viewmodels.CatFactsViewModel
import com.example.technicaltest.utils.TestConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun CatFactListRoute(
    navigateTo: (String) -> Unit,
    viewModel: CatFactsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    CatFactListScreen(
        navigateTo = navigateTo,
        uiState = uiState,
        refresh = viewModel::refresh,
        isRefreshing = isRefreshing
    )
}

@ExperimentalMaterialApi
@Composable
fun CatFactListScreen(
    navigateTo: (String) -> Unit,
    uiState: CatFactsScreenUiState,
    refresh: () -> Unit,
    isRefreshing: Boolean
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
            val pullRefreshState = rememberPullRefreshState(isRefreshing, refresh)
            Box(
                modifier = Modifier
                    .testTag(TestConstants.CAT_FACT_LIST)
                    .pullRefresh(state = pullRefreshState)
            ) {
                CatFactsListComponent(
                    catFactsList = uiState.catFacts,
                    navigateTo = navigateTo
                )
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }

        is CatFactsScreenUiState.Error -> {
            Box(
                modifier = Modifier
                    .testTag(TestConstants.CAT_FACTS_ERROR)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.message)
            }
        }
    }
}
