package com.example.voice_recorder.data.repository

import com.example.voice_recorder.data.local.RecordsDatabase
import com.example.voice_recorder.data.local.items.RecordItem
import com.example.voice_recorder.domain.repository.RecordsRepository
import javax.inject.Inject

class RecordsRepositoryImplementation @Inject constructor(
    private val db: RecordsDatabase
) : RecordsRepository {
    private val dao = db.recordItemsDao()

    override suspend fun getAllRecords() =
        dao.getAllRecords()

    override suspend fun saveNewRecord(item: RecordItem) =
        dao.insertRecordItem(item)

    override suspend fun deleteSavedRecordById(id: Int) =
        dao.deleteRecordById(id)

    override suspend fun deleteAllSavedRecords() =
        dao.deleteAllRecords()
}