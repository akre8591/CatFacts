package com.example.technicaltest.ui.components.catfacts

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.technicaltest.data.domain.interactor.GetCatFactDetailsUseCase
import com.example.technicaltest.data.domain.model.CatFactsModel
import com.example.technicaltest.fakeclasses.FakeCatFactsRepository
import com.example.technicaltest.navigation.NavigationDestinations.CAT_FACT_ID
import com.example.technicaltest.ui.catfacts.states.CatFactDetailsScreenUiState
import com.example.technicaltest.ui.catfacts.viewmodels.CatFactDetailsViewModel
import com.example.technicaltest.utils.DataState
import com.example.technicaltest.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CatFactDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val catFactsRepository = FakeCatFactsRepository()
    private val catFactModel = CatFactsModel(id = "1212", text = "Description 1", type = "Cat")
    private lateinit var sut: CatFactDetailsViewModel

    @Before
    fun setup() {
        sut = CatFactDetailsViewModel(
            getCatFactDetailsUseCase = GetCatFactDetailsUseCase(catFactsRepository = catFactsRepository),
            savedStateHandle = SavedStateHandle(mapOf(CAT_FACT_ID to "1212"))
        )
    }

    @Test
    fun `uiState is loading`() = runTest {
        val actual = sut.uiState.value
        assert(actual is CatFactDetailsScreenUiState.Loading)
    }

    @Test
    fun `uiState is success`() = runTest {
        sut.uiState.test {
            assert(awaitItem() is CatFactDetailsScreenUiState.Loading)
            catFactsRepository.catFactDetails.emit(DataState.Success(catFactModel))
            assert(awaitItem() is CatFactDetailsScreenUiState.Success)
        }
    }

    @Test
    fun `uiState is error`() = runTest {
        sut.uiState.test {
            assert(awaitItem() is CatFactDetailsScreenUiState.Loading)
            catFactsRepository.catFactDetails.emit(DataState.Failure(Exception("Error message")))
            assert(awaitItem() is CatFactDetailsScreenUiState.Error)
        }
    }
}
