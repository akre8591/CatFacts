package com.example.technicaltest.ui.components.catfacts

import app.cash.turbine.test
import com.example.technicaltest.data.domain.model.CatFactsModel
import com.example.technicaltest.fakeclasses.FakeCatFactsRepository
import com.example.technicaltest.utils.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatFactsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val catFactsRepository = FakeCatFactsRepository()
    private val catFactsModel =
        listOf(
            CatFactsModel(id = "1212", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1213", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1214", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1215", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1216", text = "Description 1", type = "Cat")
        )
    private lateinit var sut: CatFactsViewModel

    @Before
    fun setup() {
        sut = CatFactsViewModel(catFactsRepository = catFactsRepository)
    }

    @Test
    fun `uiState is loading`() = runTest {
        val actual = sut.uiState.value
        assert(actual is CatFactsScreenUiState.Loading)
    }

    @Test
    fun `uiState is success`() = runTest {
        sut.uiState.test {
            assert(awaitItem() is CatFactsScreenUiState.Loading)
            catFactsRepository.catFacts.emit(CatFactsScreenUiState.Success(catFactsModel))
            assert(awaitItem() is CatFactsScreenUiState.Success)
        }
    }

    @Test
    fun `uiState is error`() = runTest {
        sut.uiState.test {
            assert(awaitItem() is CatFactsScreenUiState.Loading)
            catFactsRepository.catFacts.emit(CatFactsScreenUiState.Error("Error Message"))
            assert(awaitItem() is CatFactsScreenUiState.Error)
        }
    }
}
