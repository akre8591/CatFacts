package com.example.technicaltest.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.technicaltest.navigation.NavigationDestinations
import com.example.technicaltest.ui.catfacts.screens.CatFactDetailsRoute

fun NavGraphBuilder.catFactDetailsNavGraph() {
    navigation(
        startDestination = NavigationDestinations.DETAILS_SCREEN,
        route = NavigationDestinations.DETAILS_ROUTE
    ) {

        composable(route = "${NavigationDestinations.DETAILS_ROUTE}/{${NavigationDestinations.CAT_FACT_ID}}") {
            CatFactDetailsRoute()
        }
    }
}
