package com.example.technicaltest.fakeclasses

import com.example.technicaltest.remote.api.CatFactsService
import com.example.technicaltest.remote.model.CatFactsResponseModel
import com.skydoves.sandwich.ApiResponse

class FakeCatFactsService : CatFactsService {

    var catFactsResponse: ApiResponse<List<CatFactsResponseModel>>? = null

    override suspend fun getCatFacts(): ApiResponse<List<CatFactsResponseModel>> {
        return catFactsResponse ?: ApiResponse.error(IllegalArgumentException())
    }
}
