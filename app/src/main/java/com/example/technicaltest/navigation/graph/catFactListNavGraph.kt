package com.example.technicaltest.navigation.graph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.technicaltest.navigation.NavigationDestinations
import com.example.technicaltest.ui.components.catfacts.screens.CatFactListRoute
import com.example.technicaltest.utils.navigateTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoroutinesApi::class)
fun NavGraphBuilder.catFactListNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationDestinations.LIST_SCREEN,
        route = NavigationDestinations.LIST_ROUTE
    ) {

        composable(NavigationDestinations.LIST_SCREEN) {
            CatFactListRoute(navigateTo = navController::navigateTo)
        }
    }
}
