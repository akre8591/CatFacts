package com.example.technicaltest.ui.components.catfacts

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
import androidx.navigation.NavHostController
import com.example.technicaltest.utils.TestConstants
import com.example.technicaltest.utils.navigateTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun CatFactsRoute(
    navController: NavHostController,
    viewModel: CatFactsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    CatFactsScreen(
        navigateTo = navController::navigateTo,
        uiState = uiState,
        refresh = viewModel::refresh,
        isRefreshing = isRefreshing
    )
}

@ExperimentalMaterialApi
@Composable
fun CatFactsScreen(
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
                modifier = Modifier.pullRefresh(state = pullRefreshState)
            ) {
                CatFactsListComponent(catFactsList = uiState.catFacts)
                PullRefreshIndicator(
                    isRefreshing,
                    pullRefreshState,
                    Modifier.align(Alignment.TopCenter)
                )
            }
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
