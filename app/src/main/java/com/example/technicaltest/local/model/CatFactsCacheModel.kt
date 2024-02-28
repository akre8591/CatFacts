package com.example.technicaltest.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.technicaltest.data.domain.model.CatFactsModel
import com.example.technicaltest.data.domain.model.StatusModel

@Entity(tableName = "cat_facts")
data class CatFactsCacheModel(
    @PrimaryKey
    val id: String = "",
    @ColumnInfo
    val text: String = "",
    @ColumnInfo
    val type: String = "",
    @Embedded
    val status: StatusCacheModel = StatusCacheModel(),
    @ColumnInfo
    val deleted: Boolean = false,
    @ColumnInfo
    val source: String = ""
) {
    fun toDomain(): CatFactsModel =
        CatFactsModel(
            id = id,
            text = text,
            type = type,
            status = status.toDomain(),
            deleted = deleted,
            source = source
        )
}

data class StatusCacheModel(
    val verified: Boolean = false,
    val feedback: String = "",
    val sentCount: Int = 0
) {
    fun toDomain(): StatusModel =
        StatusModel(
            verified = verified,
            feedback = feedback,
            sentCount = sentCount
        )
}

fun List<CatFactsCacheModel>.toDomain(): List<CatFactsModel> {
    return this.map(CatFactsCacheModel::toDomain)
}
