package com.example.technicaltest.data.repository.catfacts

import com.example.technicaltest.utils.AppDispatcher
import com.example.technicaltest.local.dao.CatFactsDao
import com.example.technicaltest.local.model.toDomain
import com.example.technicaltest.remote.api.CatFactsService
import com.example.technicaltest.remote.model.toCache
import com.example.technicaltest.ui.components.catfacts.CatFactsScreenUiState
import com.example.technicaltest.utils.Constants.NO_FOUND_IN_CACHE
import com.example.technicaltest.utils.Constants.UNEXPECTED_ERROR
import com.example.technicaltest.utils.NetworkApi
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
    private val appDispatcher: AppDispatcher
) : CatFactsRepository {
    override fun getCatFacts() = flow {
        if (networkApi.isNetworkAvailable()) {
            val response = catFactsService.getCatFacts()
            response.suspendOnSuccess {
                catFactsDao.insertCatFacts(data.toCache())
            }.suspendOnException {
                emit(CatFactsScreenUiState.Error(exception.message ?: UNEXPECTED_ERROR))
            }
        }
        val localCatFacts = catFactsDao.getCatFacts().firstOrNull()
        if (!localCatFacts.isNullOrEmpty()) {
            emit(CatFactsScreenUiState.Success(localCatFacts.toDomain()))
        } else {
            emit(CatFactsScreenUiState.Error(NO_FOUND_IN_CACHE))
        }
    }.onStart {
        emit(CatFactsScreenUiState.Loading)
    }.flowOn(appDispatcher.io())
}
