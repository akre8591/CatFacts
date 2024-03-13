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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.technicaltest.R
import com.example.technicaltest.navigation.MainNavigation
import com.example.technicaltest.navigation.NavigationDestinations.LIST_ROUTE
import com.example.technicaltest.ui.theme.TechnicalTestTheme
import com.example.technicaltest.utils.goBack

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
                        Text(text = stringResource(id = R.string.app_title), color = Color.White)
                    },
                    navigationIcon = {
                        if (displayNavigationICon) {
                            IconButton(onClick = navController::goBack) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "backIcon",
                                    tint = Color.White
                                )
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
