package com.example.technicaltest.ui.components.catfacts

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.technicaltest.data.domain.model.CatFactsModel
import com.example.technicaltest.ui.catfacts.screens.CatFactListScreen
import com.example.technicaltest.ui.catfacts.states.CatFactsScreenUiState
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
    fun `given a loading state, should show shimmer loading component`() {
        setContent()

        composeTestRule.onNodeWithTag(TestConstants.CAT_FACTS_LOADING)
            .assertIsDisplayed()
    }

    @Test
    fun `given a success state, should show cat fact list`() {
        val catFactsModel = listOf(
            CatFactsModel(id = "1212", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1213", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1214", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1215", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1216", text = "Description 1", type = "Cat")
        )
        setContent(catFactsScreenUiState = CatFactsScreenUiState.Success(catFactsModel))

        composeTestRule.onNodeWithTag(TestConstants.CAT_FACT_LIST)
            .assertIsDisplayed()
    }

    @Test
    fun `given a error state, should show cat fact list`() {
        setContent(catFactsScreenUiState = CatFactsScreenUiState.Error("Error message"))

        composeTestRule.onNodeWithTag(TestConstants.CAT_FACTS_ERROR)
            .assertIsDisplayed()
    }

    private fun setContent(
        navigateTo: (String) -> Unit = { },
        catFactsScreenUiState: CatFactsScreenUiState = CatFactsScreenUiState.Loading,
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
