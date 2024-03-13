package com.example.technicaltest.data.domain.interactor

import app.cash.turbine.test
import com.example.technicaltest.data.domain.model.CatFactsModel
import com.example.technicaltest.fakeclasses.FakeCatFactsRepository
import com.example.technicaltest.ui.components.catfacts.states.CatFactsScreenUiState
import com.example.technicaltest.utils.DataState
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCatFactsUseCaseTest {

    lateinit var sut: GetCatFactsUseCase

    private val catFactsRepository = FakeCatFactsRepository()

    @Before
    fun setup() {
        sut = GetCatFactsUseCase(catFactsRepository = catFactsRepository)
    }

    @Test
    fun `get cat facts should return loading`() = runTest {
        catFactsRepository.catFacts.emit(DataState.Progress(true))
        sut.invoke().test {
            assert(awaitItem() is CatFactsScreenUiState.Loading)
        }
    }

    @Test
    fun `get cat facts should return success`() = runTest {
        val catFactsModel = listOf(
            CatFactsModel(id = "1212", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1213", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1214", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1215", text = "Description 1", type = "Cat"),
            CatFactsModel(id = "1216", text = "Description 1", type = "Cat")
        )
        catFactsRepository.catFacts.emit(DataState.Success(catFactsModel))
        sut.invoke().test {
            assert(awaitItem() is CatFactsScreenUiState.Success)
        }
    }

    @Test
    fun `get cat facts should return error`() = runTest {
        catFactsRepository.catFacts.emit(DataState.Failure(Exception(), 404))
        sut.invoke().test {
            assert(awaitItem() is CatFactsScreenUiState.Error)
        }
    }
}
