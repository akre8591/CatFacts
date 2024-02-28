package com.example.technicaltest.remote.model

import com.example.technicaltest.local.model.CatFactsCacheModel
import com.example.technicaltest.local.model.StatusCacheModel

data class CatFactsResponseModel(
    val _id: String = "",
    val text: String = "",
    val type: String = "",
    val status: Status = Status(),
    val deleted: Boolean = false,
    val source: String = ""
) {
    fun toCache() = CatFactsCacheModel(
        id = _id,
        text = text,
        type = type,
        status = status.toCache(),
        deleted = deleted,
        source = source
    )
}

data class Status(
    val verified: Boolean = false,
    val feedback: String = "",
    val sentCount: Int = 0
) {
    fun toCache() = StatusCacheModel(
        verified = verified,
        feedback = feedback,
        sentCount = sentCount
    )
}

fun List<CatFactsResponseModel>.toCache(): List<CatFactsCacheModel> {
    return this.map(CatFactsResponseModel::toCache)
}
