package com.example.technicaltest.data.repository.catfacts

import app.cash.turbine.test
import com.example.technicaltest.fakeclasses.FakeCatFactsDao
import com.example.technicaltest.fakeclasses.FakeCatFactsService
import com.example.technicaltest.fakeclasses.FakeNetworkApi
import com.example.technicaltest.local.model.CatFactsCacheModel
import com.example.technicaltest.remote.model.CatFactsResponseModel
import com.example.technicaltest.utils.DataState
import com.example.technicaltest.utils.MainDispatcherRule
import com.example.technicaltest.utils.TestDispatcherImpl
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.util.concurrent.TimeoutException

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
    private val catFactsRemote = listOf(
        CatFactsResponseModel(_id = "1212", text = "Description 1", type = "Cat"),
        CatFactsResponseModel(_id = "1213", text = "Description 1", type = "Cat"),
        CatFactsResponseModel(_id = "1214", text = "Description 1", type = "Cat"),
        CatFactsResponseModel(_id = "1215", text = "Description 1", type = "Cat"),
        CatFactsResponseModel(_id = "1216", text = "Description 1", type = "Cat"),
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
            assert(awaitItem() is DataState.Progress)
            assert(awaitItem() is DataState.Success)
            awaitComplete()
        }
    }

    @Test
    fun `cat facts are getting from remote and saved in cache when internet is not availabl`() =
        runTest {
            networkApi.networkAvailable = true
            catFactsService.catFactsResponse = ApiResponse.of {
                Response.success(
                    catFactsRemote
                )
            }
            sut.getCatFacts().test {
                assert(awaitItem() is DataState.Progress)
                assert(awaitItem() is DataState.Success)
                awaitComplete()
            }
        }

    @Test
    fun `cat facts are not getting from remote and saved in cache when internet is not available`() =
        runTest {
            networkApi.networkAvailable = true
            catFactsService.catFactsResponse = ApiResponse.error(TimeoutException())
            sut.getCatFacts().test {
                assert(awaitItem() is DataState.Progress)
                assert(awaitItem() is DataState.Failure)
                assert(awaitItem() is DataState.Failure)
                awaitComplete()
            }
        }

    @Test
    fun `cat fact details are getting from cache`() = runTest {
        catFactsDao.insertCatFacts(catFactsCacheModel)
        sut.getCatFactDetails(id = "1212").test {
            assert(awaitItem() is DataState.Progress)
            assert(awaitItem() is DataState.Success)
            awaitComplete()
        }
    }

    @Test
    fun `cat fact details don't get any value from cache`() = runTest {
        catFactsDao.insertCatFacts(catFactsCacheModel)
        sut.getCatFactDetails(id = "1217").test {
            assert(awaitItem() is DataState.Progress)
            assert(awaitItem() is DataState.Failure)
            awaitComplete()
        }
    }
}
