package com.example.voice_recorder.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.voice_recorder.data.local.items.RecordItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class RecordItemsDaoTest {
    private lateinit var db: RecordsDatabase
    private lateinit var dao: RecordItemsDao
    private lateinit var testItem: RecordItem

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecordsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = db.recordItemsDao()

        testItem = RecordItem()
    }

    @After
    @Throws(IOException::class)
    fun fallDown() {
        db.close()
    }

    @Test
    fun insertItemTest() = runTest {
        dao.insertRecordItem(testItem)
        val items = dao.getAllRecords().first()
        assertThat(items).hasSize(1)
    }

    @Test
    fun insertTwoItemsWithSameIdAndCheckSizeChangesTest() = runTest {
        dao.insertRecordItem(RecordItem(1))
        dao.insertRecordItem(RecordItem(1))
        val items = dao.getAllRecords().first()
        assertThat(items).hasSize(1)
    }

    @Test
    fun insertItemAndThanDeleteItTest() = runTest {
        dao.insertRecordItem(testItem)
        dao.deleteRecordById(1)
        val items = dao.getAllRecords().first()
        assertThat(items).isEmpty()
    }

    @Test
    fun insertItemsAndDeleteAllTest() = runTest {
        repeat(3) {
            dao.insertRecordItem(testItem)
        }
        dao.deleteAllRecords()
        val items = dao.getAllRecords().first()
        assertThat(items).isEmpty()
    }
}