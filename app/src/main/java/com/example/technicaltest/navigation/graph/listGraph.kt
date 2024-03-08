package com.example.technicaltest.navigation.graph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.technicaltest.navigation.NavigationDestinations
import com.example.technicaltest.ui.components.catfacts.CatFactsRoute

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.listGraph(
    navController: NavHostController,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = NavigationDestinations.LIST_SCREEN,
        route = NavigationDestinations.LIST_ROUTE
    ) {

        composable(NavigationDestinations.LIST_SCREEN) {
            CatFactsRoute(navController)
        }

        nestedGraphs()
    }
}
