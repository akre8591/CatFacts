package com.example.technicaltest.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.technicaltest.local.dao.CatFactsDao
import com.example.technicaltest.local.model.CatFactsCacheModel

@Database(entities = [CatFactsCacheModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catFacts(): CatFactsDao
}
