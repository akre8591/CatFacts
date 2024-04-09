package com.example.technicaltest.data.domain.interactor

import app.cash.turbine.test
import com.example.technicaltest.data.domain.model.CatFactsModel
import com.example.technicaltest.fakeclasses.FakeCatFactsRepository
import com.example.technicaltest.ui.catfacts.states.CatFactDetailsScreenUiState
import com.example.technicaltest.utils.DataState
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCatFactDetailsUseCaseTest {

    private lateinit var sut: GetCatFactDetailsUseCase

    private val catFactsRepository = FakeCatFactsRepository()

    @Before
    fun setup() {
        sut = GetCatFactDetailsUseCase(catFactsRepository = catFactsRepository)
    }

    @Test
    fun `get cat fact details should return loading`() = runTest {
        catFactsRepository.catFactDetails.emit(DataState.Progress(true))
        sut.invoke(id = "1212").test {
            assert(awaitItem() is CatFactDetailsScreenUiState.Loading)
        }
    }

    @Test
    fun `get cat fact details should return success`() = runTest {
        catFactsRepository.catFactDetails.emit(DataState.Success(CatFactsModel()))
        sut.invoke(id = "1212").test {
            assert(awaitItem() is CatFactDetailsScreenUiState.Success)
        }
    }

    @Test
    fun `get cat fact details should return error`() = runTest {
        catFactsRepository.catFactDetails.emit(DataState.Failure(Exception(), 404))
        sut.invoke(id = "1212").test {
            assert(awaitItem() is CatFactDetailsScreenUiState.Error)
        }
    }
}
