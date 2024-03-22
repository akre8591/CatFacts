package com.example.technicaltest.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.technicaltest.local.model.CatFactsCacheModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CatFactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatFacts(catFacts: List<CatFactsCacheModel>)

    @Query("SELECT * FROM cat_facts")
    fun getCatFacts(): Flow<List<CatFactsCacheModel>>

    @Query("SELECT * FROM cat_facts WHERE id = :id")
    fun getCatFactDetails(id: String): Flow<CatFactsCacheModel>
}
