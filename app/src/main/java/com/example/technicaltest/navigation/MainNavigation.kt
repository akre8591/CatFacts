package com.example.technicaltest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.technicaltest.navigation.graph.catFactDetailsNavGraph
import com.example.technicaltest.navigation.graph.catFactListNavGraph

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.LIST_ROUTE,
        modifier = modifier
    ) {
        catFactListNavGraph(navController = navController) {
            catFactDetailsNavGraph()
        }
    }
}
