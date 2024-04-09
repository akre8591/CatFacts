package com.example.technicaltest.fakeclasses

import com.example.technicaltest.data.domain.model.CatFactsModel
import com.example.technicaltest.data.repository.catfacts.CatFactsRepository
import com.example.technicaltest.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCatFactsRepository : CatFactsRepository {
    val catFacts = MutableStateFlow(DataState.loading<List<CatFactsModel>>(false))
    val catFactDetails = MutableStateFlow(DataState.loading<CatFactsModel>(false))
    override fun getCatFacts(): Flow<DataState<List<CatFactsModel>>> = catFacts
    override fun getCatFactDetails(id: String): Flow<DataState<CatFactsModel>> = catFactDetails
}
