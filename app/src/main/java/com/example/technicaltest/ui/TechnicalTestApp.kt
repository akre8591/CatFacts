package com.example.technicaltest.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.technicaltest.navigation.MainNavigation
import com.example.technicaltest.navigation.NavigationDestinations.LIST_ROUTE
import com.example.technicaltest.ui.theme.TechnicalTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicalTestApp() {
    TechnicalTestTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination: NavDestination? = navBackStackEntry?.destination
        val currentScreen: String? = currentDestination?.parent?.route
        val displayNavigationICon: Boolean = currentScreen != LIST_ROUTE
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Technical Test App", color = Color.White)
                    },
                    navigationIcon = {
                        if (displayNavigationICon) {
                            IconButton(onClick = { }) {
                                Icon(Icons.Filled.ArrowBack, "backIcon")
                            }
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(
                            0xFF002F51
                        )
                    )
                )
            },
            content = { padding ->
                MainNavigation(
                    modifier = Modifier.padding(padding),
                    navController = navController
                )
            }
        )
    }
}
