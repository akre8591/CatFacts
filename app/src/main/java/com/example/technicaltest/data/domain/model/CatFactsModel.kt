package com.example.technicaltest.data.domain.model

data class CatFactsModel(
    val id: String = "",
    val text: String = "",
    val type: String = "",
    val status: StatusModel = StatusModel(),
    val deleted: Boolean = false,
    val source: String = ""
)

data class StatusModel(
    val verified: Boolean = false,
    val feedback: String = "",
    val sentCount: Int = 0
)
