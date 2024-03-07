package com.example.technicaltest.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.example.technicaltest.local.dao.CatFactsDao
import com.example.technicaltest.local.database.AppDatabase
import com.example.technicaltest.local.model.CatFactsCacheModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CatFactsDaoTest {

    private lateinit var sut: CatFactsDao
    private lateinit var db: AppDatabase
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val catFactsCacheModel = listOf(
        CatFactsCacheModel(id = "1212", text = "Description 1", type = "Cat"),
        CatFactsCacheModel(id = "1213", text = "Description 2", type = "Cat"),
        CatFactsCacheModel(id = "1214", text = "Description 3", type = "Cat"),
        CatFactsCacheModel(id = "1215", text = "Description 4", type = "Cat"),
        CatFactsCacheModel(id = "1216", text = "Description 5", type = "Cat"),
    )

    @Before
    fun setup() {
        db = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        sut = db.catFacts()
    }

    @Test
    fun `given a list of cat facts, should save the elements`() = runTest {

        sut.insertCatFacts(catFactsCacheModel)

        sut.getCatFacts().test {
            assert(awaitItem().size == 5)
        }
    }

    @After
    fun tearDown() {
        db.close()
    }
}
