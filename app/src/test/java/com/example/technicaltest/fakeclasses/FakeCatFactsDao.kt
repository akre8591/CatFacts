package com.example.technicaltest.fakeclasses

import com.example.technicaltest.local.dao.CatFactsDao
import com.example.technicaltest.local.model.CatFactsCacheModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCatFactsDao : CatFactsDao {

    private val inMemoryList = mutableListOf<CatFactsCacheModel>()

    override fun insertCatFacts(catFacts: List<CatFactsCacheModel>) {
        inMemoryList.addAll(catFacts)
    }

    override fun getCatFacts(): Flow<List<CatFactsCacheModel>> {
        return flowOf(inMemoryList)
    }

    override fun getCatFactDetails(id: String): Flow<CatFactsCacheModel> {
        TODO("Not yet implemented")
    }
}
