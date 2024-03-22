package com.example.technicaltest.data.repository.catfacts

import com.example.technicaltest.data.domain.model.CatFactsModel
import com.example.technicaltest.local.dao.CatFactsDao
import com.example.technicaltest.local.model.toDomain
import com.example.technicaltest.remote.api.CatFactsService
import com.example.technicaltest.remote.model.toCache
import com.example.technicaltest.utils.AppDispatcher
import com.example.technicaltest.utils.Constants.NO_FOUND_IN_CACHE
import com.example.technicaltest.utils.DataState
import com.example.technicaltest.utils.NetworkApi
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CatFactsRepositoryImpl @Inject constructor(
    private val catFactsService: CatFactsService,
    private val catFactsDao: CatFactsDao,
    private val networkApi: NetworkApi,
    private val appDispatcher: AppDispatcher,
) : CatFactsRepository {

    override fun getCatFacts() = flow<DataState<List<CatFactsModel>>> {
        if (networkApi.isNetworkAvailable()) {
            val response = catFactsService.getCatFacts()
            response.suspendOnSuccess {
                catFactsDao.insertCatFacts(data.toCache())
            }.suspendOnException {
                val throwable = Throwable(message())
                emit(DataState.Failure(throwable))
            }
        }
        val localCatFacts = catFactsDao.getCatFacts().firstOrNull()
        if (!localCatFacts.isNullOrEmpty()) {
            emit(DataState.Success((localCatFacts.toDomain())))
        } else {
            val throwable = Throwable(NO_FOUND_IN_CACHE)
            emit(DataState.Failure(throwable))
        }
    }.onStart {
        emit(DataState.loading(isLoading = true))
    }.flowOn(appDispatcher.io())

    override fun getCatFactDetails(id: String) = flow<DataState<CatFactsModel>> {
        val localCatFactDetails = catFactsDao.getCatFactDetails(id = id).firstOrNull()
        if (localCatFactDetails != null) {
            emit(DataState.Success((localCatFactDetails.toDomain())))
        } else {
            val throwable = Throwable(NO_FOUND_IN_CACHE)
            emit(DataState.Failure(throwable))
        }
    }.onStart {
        emit(DataState.loading(isLoading = true))
    }.flowOn(appDispatcher.io())
}
