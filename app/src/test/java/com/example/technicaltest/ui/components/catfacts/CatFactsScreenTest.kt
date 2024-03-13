package com.example.technicaltest.ui.components.catfacts

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.technicaltest.ui.components.catfacts.screens.CatFactListScreen
import com.example.technicaltest.ui.components.catfacts.states.CatFactsScreenUiState
import com.example.technicaltest.ui.theme.TechnicalTestTheme
import com.example.technicaltest.utils.TestConstants
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalMaterialApi
@RunWith(RobolectricTestRunner::class)
class CatFactsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given loading screen, should show shimmer loading component`() {
        setContent()

        composeTestRule.onNodeWithTag(TestConstants.CAT_FACTS_LOADING)
            .assertIsDisplayed()
    }

    private fun setContent(
        navigateTo: (String) -> Unit = { },
        catFactsScreenUiState: CatFactsScreenUiState = CatFactsScreenUiState.Loading
    ) {
        composeTestRule.setContent {
            TechnicalTestTheme {
                CatFactListScreen(
                    navigateTo = navigateTo,
                    uiState = catFactsScreenUiState,
                    refresh = { },
                    isRefreshing = false
                )
            }
        }
    }
}
