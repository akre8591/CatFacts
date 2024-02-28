package com.example.technicaltest.remote.api

import com.example.technicaltest.remote.model.CatFactsResponseModel
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface CatFactsService {

    @GET("/facts")
    suspend fun getCatFacts(): ApiResponse<List<CatFactsResponseModel>>
}
