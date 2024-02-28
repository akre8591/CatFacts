package com.example.technicaltest.data.repository.catfacts

import app.cash.turbine.test
import com.example.technicaltest.fakeclasses.FakeCatFactsDao
import com.example.technicaltest.fakeclasses.FakeCatFactsService
import com.example.technicaltest.fakeclasses.FakeNetworkApi
import com.example.technicaltest.local.model.CatFactsCacheModel
import com.example.technicaltest.ui.components.catfacts.CatFactsScreenUiState
import com.example.technicaltest.utils.MainDispatcherRule
import com.example.technicaltest.utils.TestDispatcherImpl
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatFactsRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val catFactsService = FakeCatFactsService()
    private val networkApi = FakeNetworkApi()
    private val catFactsDao = FakeCatFactsDao()
    private val appDispatcher = TestDispatcherImpl()
    private lateinit var sut: CatFactsRepository
    private val catFactsCacheModel = listOf(
        CatFactsCacheModel(id = "1212", text = "Description 1", type = "Cat"),
        CatFactsCacheModel(id = "1213", text = "Description 2", type = "Cat"),
        CatFactsCacheModel(id = "1214", text = "Description 3", type = "Cat"),
        CatFactsCacheModel(id = "1215", text = "Description 4", type = "Cat"),
        CatFactsCacheModel(id = "1216", text = "Description 5", type = "Cat"),
    )

    @Before
    fun setup() {
        sut = CatFactsRepositoryImpl(
            catFactsService = catFactsService,
            catFactsDao = catFactsDao,
            networkApi = networkApi,
            appDispatcher = appDispatcher
        )
    }

    @Test
    fun `cat facts are getting from cache data when internet is not available`() = runTest {
        networkApi.networkAvailable = false
        catFactsDao.insertCatFacts(catFactsCacheModel)
        sut.getCatFacts().test {
            assert(awaitItem() is CatFactsScreenUiState.Loading)
            assert(awaitItem() is CatFactsScreenUiState.Success)
            awaitComplete()
        }
    }
}
